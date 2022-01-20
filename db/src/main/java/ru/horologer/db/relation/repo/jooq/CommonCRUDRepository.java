package ru.horologer.db.relation.repo.jooq;

import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import ru.horologer.base.MethodExecutor;
import ru.horologer.base.StringConverter;
import ru.horologer.core.AppConfiguration;
import ru.horologer.db.relation.model.CommonRepoResponse;
import ru.horologer.db.relation.model.FlatValue;
import ru.horologer.db.relation.model.FlatValueRecord;
import ru.horologer.db.relation.model.InnerRepoRequest;
import ru.horologer.db.relation.repo.transaction.BreakTransactionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;
import static ru.horologer.base.StringConverter.toObject;
import static ru.horologer.core.AppConfiguration.getLogger;


public class CommonCRUDRepository {

    //TODO make only Table<Record> and Field<Object> fields
    //TODO if you want dynamic attribute model you need update fields for every repo
    public static final String BASE_ID_NAME = "id";
    private String tableName;
    private List<String> fields = new ArrayList<>();
    private Table<Record> table;

    public static FlatValueRecord getListFromRecord(Record record) {
        List<FlatValue> row = new ArrayList<>();
        if (record != null) {
            for (Field<?> field : record.fields()) {
                row.add(new FlatValue().setName(field.getName()).setValue(field.getValue(record) == null ? "" : StringConverter.toString(field.getValue(record))));
            }
        }
        return new FlatValueRecord().setFieldList(row);
    }

    //TODO try to remove not initialized variables
    public CommonRepoResponse create(InnerRepoRequest request) {
        CommonRepoResponse commonRepoResponse = new CommonRepoResponse();

        DSLContext dslContext = DSL.using(request.getConn());
        InsertSetStep<Record> baseInsert = dslContext.insertInto(table);
        InsertResultStep<Record> finalInsert;
        //TODO extract method and get it no_comment naming
        if (request.getPublicRequest().getRecord().getFieldList().isEmpty()) {
            finalInsert = baseInsert.defaultValues().returning();
            AppConfiguration.getLogger().logOnLocal(finalInsert.getSQL());
            commonRepoResponse.setRecord(request.getPublicRequest().getRecord());
            finalInsert.fetchOptional().ifPresent((Record rec) -> commonRepoResponse
                    .getRecord()
                    .addValueByName(BASE_ID_NAME, StringConverter.toString(rec.getValue(getIdField()))));
            return commonRepoResponse;
        }

        //TODO extract method and get it no_comment naming
        FlatValue flatValue = request.getPublicRequest().getRecord().getFieldList().get(0);
        InsertSetMoreStep<Record> nextInsert = baseInsert.set(getField(flatValue.getName()), toObject(flatValue.getValue()));
        for (int i = 1; i < request.getPublicRequest().getRecord().getFieldList().size(); i++) {
            flatValue = request.getPublicRequest().getRecord().getFieldList().get(i);
            nextInsert = nextInsert.set(getField(flatValue.getName()), toObject(flatValue.getValue()));
        }

        Field<Integer> idField;
        finalInsert = (idField = getIdField()) != null ? nextInsert.returning(idField) : nextInsert.returning();
        AppConfiguration.getLogger().logOnLocal(finalInsert.getSQL());

        commonRepoResponse.setRecord(request.getPublicRequest().getRecord());
        finalInsert.fetchOptional().ifPresent((Record rec) -> commonRepoResponse
                .getRecord()
                .addValueByName(BASE_ID_NAME, StringConverter.toString(rec.getValue(getIdField()))));
        return commonRepoResponse;
    }

    public CommonRepoResponse read(InnerRepoRequest request) {
        DSLContext dslContext = DSL.using(request.getConn());

        SelectConditionStep<Record> where = dslContext
                .select(getFields(request.getPublicRequest().getFields()))
                .from(table)
                .where(getCondition(request));

        getLogger().logOnLocal(where.getSQL());

        return new CommonRepoResponse()
                .setRecords(new MethodExecutor<RecordMapper<Record, FlatValueRecord>, List<FlatValueRecord>>()
                        .setValue(CommonCRUDRepository::getListFromRecord)
                        .setMain(where::fetch)
                        .setExceptionHandler((e) -> {
                            throw new BreakTransactionException();
                        })
                        .execute());

    }


    public CommonRepoResponse update(InnerRepoRequest request) {
        UpdateSetFirstStep<Record> baseUpdate = DSL.using(request.getConn())
                .update(table);

        UpdateSetMoreStep<Record> nextUpdate = null;
        for (FlatValue flatValue : request.getPublicRequest().getRecord().getFieldList()) {
            nextUpdate = baseUpdate.set(getField(flatValue.getName()), flatValue.getValue());
        }
        UpdateConditionStep<Record> where = requireNonNull(nextUpdate).where(getCondition(request));
        AppConfiguration.getLogger().logOnLocal(where.getSQL());
        where.returning().fetch();

        return new CommonRepoResponse();
    }

    public CommonRepoResponse delete(InnerRepoRequest request) {
        DeleteConditionStep<Record> where = DSL.using(request.getConn())
                .delete(table)
                .where(getCondition(request));
        AppConfiguration.getLogger().logOnLocal(where.getSQL());
        return new CommonRepoResponse();
    }

    public CommonRepoResponse count(InnerRepoRequest request) {
        return new CommonRepoResponse();
    }

    //TODO check that readed and deleted values is the same (by count only i think)
    public CommonRepoResponse deleteAndGet(InnerRepoRequest request) {
        CommonRepoResponse read = read(request);
        delete(request);
        return read;
    }
    //TODO check that readed and updated values is the same (by count only i think)
    public CommonRepoResponse updateAndGet(InnerRepoRequest request) {
        CommonRepoResponse read = read(request);
        update(request);
        return read;
    }

    public String getTableName() {
        return tableName;
    }

    public CommonCRUDRepository setTableName(String tableName) {
        this.tableName = tableName;
        table = DSL.table(tableName);
        return this;
    }

    public CommonCRUDRepository setFields(List<String> fields) {
        this.fields = fields;
        return this;
    }

    public CommonCRUDRepository addField(String field) {
        this.fields.add(field);
        return this;
    }

    //TODO make List of Field Object in class field instead of list strings
    @SuppressWarnings({"unchecked"})
    private Field<Object>[] getFields(List<String> names) {
        Field<Object>[] f = (Field<Object>[]) new Field[names.size()];
        for (int i = 0; i < names.size(); i++) {
            f[i] = DSL.field(DSL.name(DSL.quotedName(tableName), DSL.quotedName(names.get(i))));
        }
        return f;
    }

    private Field<Object> getField(String name) {
        return DSL.field(DSL.name(DSL.quotedName(tableName), DSL.quotedName(name)));
    }

    private Field<Integer> getIdField() {
        return fields.contains(BASE_ID_NAME) ? DSL.field(DSL.name(DSL.quotedName(tableName), DSL.quotedName(BASE_ID_NAME)), Integer.class) : null;
    }

    private Condition getCondition(InnerRepoRequest request) {
        Condition condition = DSL.trueCondition();
        for (Map.Entry<String, Object> entry : request.getPublicRequest().getFilters().entrySet()) {
            condition = condition.and(getField(entry.getKey()).eq(entry.getValue()));
        }
        return condition;
    }
}
