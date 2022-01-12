package ru.horologer.core;

import org.apache.juli.ClassLoaderLogManager;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppLogger {

    private static Logger LOG;
    private final int logLevel;

    public AppLogger(String loggerName, int appProfileLevel) {
        logLevel = appProfileLevel;
        LOG = ClassLoaderLogManager.getLogManager().getLogger(loggerName);
        if (LOG == null) {
            LOG = Logger.getGlobal();
        }
    }

    public void log(String message) {
        log(AppProfile.PROD, message);
    }

    public void logOnTest(String message) {
        log(AppProfile.TEST, message);
    }

    public void logOnLocal(String message) {
        log(AppProfile.LOCAL, message);
    }

    private void log(AppProfile level, String message) {
        if (level.ordinal() >= logLevel)
            LOG.log(Level.INFO, level + ":" + message);
    }

    public void log(Throwable throwable) {
        log(AppProfile.PROD, throwable);
    }

    public void log(AppProfile lvl, Throwable throwable) {
        log(lvl, "", throwable);
    }

    public void log(AppProfile lvl, String message, Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pr = new PrintWriter(sw);
        throwable.printStackTrace(pr);
        pr.flush();
        pr.close();
        log(lvl, message + System.lineSeparator() + sw);
    }

}
