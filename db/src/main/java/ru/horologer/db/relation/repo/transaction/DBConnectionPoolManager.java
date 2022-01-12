package ru.horologer.db.relation.repo.transaction;

import java.util.HashMap;
import java.util.Map;

import static ru.horologer.core.AppConfiguration.getConfigManager;

public class DBConnectionPoolManager {

    private final Map<String, DBConnectionPool> pools = new HashMap<>();

    public void addPool(String poolName, DBConnectionPool pool){
        pools.put(poolName, pool);
    }

    public void addDefault(String poolName){
//        Objects.requireNonNull() - required fields TODO insert this checks
//        Objects.requireNonNullElseGet(null,) - default values

        pools.put(poolName, new DBConnectionPool(new ApachePoolPropertiesDecorator().setUrl(getConfigManager().getProp("db.url"))
                .setDriverClassName(getConfigManager().getProp("db.driver"))
                .setUsername(getConfigManager().getProp("db.user"))
                .setPassword(getConfigManager().getProp("db.pass"))
                .setJmxEnabled(true)
                .setTestWhileIdle(false)
                .setTestOnBorrow(true)
                .setValidationQuery("SELECT 1")
                .setTestOnReturn(false)
                .setValidationInterval(30000)
                .setTimeBetweenEvictionRunsMillis(30000)
                .setMaxActive(100)
                .setInitialSize(10)
                .setMaxWait(10000)
                .setMinEvictableIdleTimeMillis(30000)
                .setMinIdle(10)
                .setLogAbandoned(true)//TODO make configurable by app profile
                .setRemoveAbandoned(true)
                .setRemoveAbandonedTimeout(60)));
    }

    public DBConnectionPool getPool(String name){
        return pools.getOrDefault(name, null);
    }


}
