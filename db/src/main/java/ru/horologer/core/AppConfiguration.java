package ru.horologer.core;

import ru.horologer.db.relation.repo.transaction.DBConnectionPoolManager;

/**
 * Class for keeping your application configuration - configs, connections with DBs,Files, StateManagers
 * logging configuration and thread pools and otherwise
 */
public class AppConfiguration {
    private static PropertyConfigManager configManager;
    private static AppLogger logger;
    private static DBConnectionPoolManager dbManager;

    /**
     * Method to init on application start, can used for re-init.
     * Thread-Safety for init and re-init
     * TODO make init function in method params for additional user initiation
     * TODO make test for this, static synchronized must lock whole class and static functions
     * @param propFiles property files for ConfigManager if they need (if you using configuration from property files)
     */
    public static synchronized void init(String... propFiles) {
        configManager = new PropertyConfigManager();
        for (String propFile : propFiles) {
            configManager.appendPropFiles(propFile);
        }
        logger = new AppLogger(configManager.getProp("log.name"),
                AppProfile.valueOf(configManager.getProp("app.profile").toUpperCase()).ordinal());
        dbManager = new DBConnectionPoolManager();
    }

    /**
     * TODO make abstract config manager and other implementations (remote config as min)
     * @return config manager with all configs
     */
    public static PropertyConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * TODO make abstract logger and other implementations (remote logging as min)
     * @return logger for application
     */
    public static AppLogger getLogger() {
        return logger;
    }

    /**
     * TODO make abstract state manager and other implementations (files, cache, remote system, remote files, google, yandex disks and other)
     * @return logger for application
     */
    public static DBConnectionPoolManager getDbManager() {
        return dbManager;
    }


}
