package com.csdental.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.String.format;

public class PropHelper {
    private final static Logger logger = LoggerFactory.getLogger(PropHelper.class);
    private final static Properties props = new Properties();
    private static boolean hasLoaded = false;

    private static void load(String file) {
        try (InputStream is = ClassLoader.getSystemResourceAsStream(file)) {
            if (is != null) {
                props.load(is);
                is.close();
            } else {
                logger.warn(format("%s was not provided", file));
            }
        } catch (IOException e) {
            BuildStatus.getInstance().recordError();
            logger.error(file, e);
        }
    }
    private static boolean load() {
        load(System.getProperty("cabrite.prop", "cabrite-core.properties"));
        load(System.getProperty("cabrite.prop", "cabrite-meshviewer.properties"));
        return true;
    }

    public static String getProperty(String key) {
        if (!hasLoaded) {
            hasLoaded = load();
        }

        return System.getProperty(key, props.getProperty(key));
    }
}
