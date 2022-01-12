package ru.horologer.core;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class PropertyConfigManager {

    private final List<String> propFiles = new ArrayList<>();

    private final RenewableReadCache<Properties> PROPERTY_RENEWABLE_READ_CACHE = new RenewableReadCache.CacheBuilder<Properties>()
            .setInterval(60)
            .setTimeUnit(TimeUnit.SECONDS)
            .setRefresher(this::refreshPropConfig)
            .build();

    public String getProp(String name) {
        if(PROPERTY_RENEWABLE_READ_CACHE.getCacheableValue() == null)
            return "";
        return PROPERTY_RENEWABLE_READ_CACHE.getCacheableValue().getProperty(name,"");
    }

    private Properties refreshPropConfig() {
        Properties p = new Properties();
        ClassLoader cL = Thread.currentThread().getContextClassLoader();
        if(propFiles.size() == 0)
            return null;
        propFiles.forEach((pf)->p.putAll(loadFromFile(Objects.requireNonNull(cL.getResource(pf)).getFile())));
        return p;
    }

    private  Properties loadFromFile(String path) {
        Properties prop = new Properties();
        try (InputStreamReader r = new InputStreamReader(new FileInputStream(new File(path)))) {
            prop.load(r);
        } catch (IOException e) {
            System.err.println("No property file found");
        }
        return prop;
    }

    public void appendPropFiles(String propFiles) {
        this.propFiles.add(propFiles);
    }
}
