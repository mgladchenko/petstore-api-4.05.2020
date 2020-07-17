package config;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.TimeZone;

@Slf4j
public class ConfigProperties {

    private static final Properties props = new Properties();
    private static final String PATH_TO_RESOURCES;
    public final static String BASE_URL;
    public static final String env;

    static {
        env = System.getProperty("env", "stg");
        log.info("C  O  N  F  I  G  U  R  A  T  I  O  N");
        log.info("JVM timezone: {}", TimeZone.getDefault().getID());
        log.info("Environment : " + env);
        PATH_TO_RESOURCES = "src/test/resources/env/" + env;
        readProperties();

        BASE_URL = getProperty("base.url");
    }

    private static String getProperty(String propertyName, String defaultValue) {
        return System.getProperty(propertyName.toLowerCase(), props.getProperty(propertyName, defaultValue));
    }

    private static String getProperty(String propertyName) {
        String propertyValue = getProperty(propertyName, null);
        log.info("Read property {} = {}", propertyName, propertyValue);
        return propertyValue;
    }

    private static void readProperties() throws AssertionError {
        String path = PATH_TO_RESOURCES + "/config.properties";
        try {
            log.info("Reading configuration data from resources file {}", path);
            props.load(new FileReader(path));
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            throw new AssertionError(String.format("An exception occurs during loading of '%s' config file", path), e);
        }
    }

}
