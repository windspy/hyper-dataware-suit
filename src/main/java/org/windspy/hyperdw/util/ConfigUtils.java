package org.windspy.hyperdw.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigUtils {

    private static final ConfigUtils instance = new ConfigUtils();

    private final Properties prop = new Properties();
    private ConfigUtils() {
        InputStream stream = null;
        try {
            stream = getClass().getResourceAsStream("/config.properties");
            if (stream==null) return;
            prop.load(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key, String defaultValue) {
        return instance.prop.getProperty(key, defaultValue);
    }

    public static String getProperty(String key) {
        return getProperty(key, null);
    }

    public static int getProperty(String key, int defaultValue) {
        String value = getProperty(key);
        return value != null ? Integer.parseInt(value): defaultValue;
    }

    public static double getProperty(String key, double defaultValue) {
        String value = getProperty(key);
        return value != null ? Double.parseDouble(value): defaultValue;
    }
}

