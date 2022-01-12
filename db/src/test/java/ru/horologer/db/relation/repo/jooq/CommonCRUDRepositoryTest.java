package ru.horologer.db.relation.repo.jooq;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.horologer.core.AppConfiguration;
import ru.horologer.db.relation.model.CommonRepoRequest;
import ru.horologer.db.relation.model.TMRequest;
import ru.horologer.db.relation.repo.transaction.SingleTransactionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ru.horologer.core.AppConfiguration.getDbManager;


class CommonCRUDRepositoryTest {

    private static final String TEST_POOL_NAME = "testPool";

    @BeforeAll
    static void configApp() {
        AppConfiguration.init("unit-test.properties");
        getDbManager().addDefault(TEST_POOL_NAME);
    }

    @AfterAll
    static void closeApp() {
        getDbManager().getPool(TEST_POOL_NAME).close();
    }

    @Test
    void read() {
        new SingleTransactionManager()
                .setPool(getDbManager().getPool(TEST_POOL_NAME))
                .inOneTransaction(
                        new TMRequest()
                                .setRequest(prepareTestRequest())
                                .setFunction(new CommonCRUDRepository()
                                        .setTableName("table1")
                                        .addField("field1")
                                        .addField("field2")::read),
                        new TMRequest()
                                .setRequest(prepareTestRequest())
                                .setFunction(new CommonCRUDRepository()
                                        .setTableName("table1")
                                        .addField("field1")
                                        .addField("field2")
                                        .addField("field3")::read),
                        new TMRequest()
                                .setRequest(prepareTestStringRequest())
                                .setFunction(new CommonCRUDRepository()
                                        .setTableName("table1")
                                        .addField("field1")
                                        .addField("field2")
                                        .addField("field3")::read));
    }

    private CommonRepoRequest prepareTestRequest() {
        List<String> fields = new ArrayList<>();
        fields.add("field1");
        fields.add("field2");
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("field1", "field1");
        filters.put("field2", 2);
        return new CommonRepoRequest()
                .setFields(fields)
                .setFilters(filters);
    }

    private CommonRepoRequest prepareTestStringRequest() {
        List<String> fields = new ArrayList<>();
        fields.add("field1");
        fields.add("field2");
        HashMap<String, String> filters = new HashMap<>();
        filters.put("field1", "field1");
        filters.put("field2", "2");
        return new CommonRepoRequest()
                .setFields(fields)
                .setStringFilters(filters);
    }
}