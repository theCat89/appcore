package ru.horologer.db.relation.repo.jooq;

import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import ru.horologer.base.MethodExecutor;
import ru.horologer.base.StringConverter;
import ru.horologer.db.relation.model.CommonRepoResponse;
import ru.horologer.db.relation.model.FlatValue;
import ru.horologer.db.relation.model.FlatValueRecord;
import ru.horologer.db.relation.model.InnerRepoRequest;
import ru.horologer.db.relation.repo.transaction.BreakTransactionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.horologer.core.AppConfiguration.getLogger;


public class CommonCRUDRepository {

    private String tableName;
    private List<String> fields = new ArrayList<>();

    public static FlatValueRecord getListFromRecord(Record record) {
        List<FlatValue> row = new ArrayList<>();
        if (record != null) {
            for (Field<?> field : record.fields()) {
                row.add(new FlatValue().setName(field.getName()).setValue(field.getValue(record) == null ? "" : StringConverter.toString(field.getValue(record))));
            }
        }
        return new FlatValueRecord().setFieldList(row);
    }

    public CommonRepoResponse create(InnerRepoRequest request) {
        return new CommonRepoResponse();
    }

    public CommonRepoResponse read(InnerRepoRequest request) {
        DSLContext dslContext = DSL.using(request.getConn());

        SelectConditionStep<Record> where = dslContext
                .select(getFields(request.getPublicRequest().getFields()))
                .from(DSL.table(tableName))
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
        return new CommonRepoResponse();
    }

    public CommonRepoResponse delete(InnerRepoRequest request) {
        return new CommonRepoResponse();
    }

    public CommonRepoResponse count(InnerRepoRequest request) {
        return new CommonRepoResponse();
    }

    public CommonRepoResponse deleteAndGet(InnerRepoRequest request) {
        CommonRepoResponse read = read(request);
        delete(request);
        return read;
    }

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

    private Condition getCondition(InnerRepoRequest request) {
        Condition condition = DSL.trueCondition();
        for (Map.Entry<String, Object> entry : request.getPublicRequest().getFilters().entrySet()) {
            condition = condition.and(DSL.field(DSL.name(DSL.quotedName(tableName), DSL.quotedName(entry.getKey()))).eq(entry.getValue()));
        }
        return condition;
    }
}
