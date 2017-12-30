package by.runets.buber.infrastructure.util;

import by.runets.buber.infrastructure.exception.IOFileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileManager {
    private final static Logger LOGGER = LogManager.getLogger(PropertyFileManager.class);
    private Properties properties;

    public PropertyFileManager(String path) throws IOFileException {
        initialize(path);
    }

    private void initialize(String path) throws IOFileException {
        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IOFileException("File not found " + e);
        }
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
