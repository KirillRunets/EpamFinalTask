package by.runets.buber.util;

import by.runets.buber.infrastructure.constant.PropertyKey;
import by.runets.buber.infrastructure.constant.PropertyPath;
import by.runets.buber.infrastructure.exception.EncryptorException;
import by.runets.buber.infrastructure.exception.IOFileException;
import by.runets.buber.infrastructure.util.PasswordEncrypt;
import by.runets.buber.infrastructure.util.PropertyFileManager;
import by.runets.buber.infrastructure.util.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UtilTest {
    @Test
    public void testEncryptPassword() throws EncryptorException {
        String expected = "pass";
        String actual = "1a1dc91c907325c69271ddf0c944bc72";
        Assert.assertEquals(actual, PasswordEncrypt.encryptPassword(expected));
    }

    @Test
    public void testGetProperty() throws IOFileException {
        PropertyFileManager propertyFileManager = new PropertyFileManager(PropertyPath.CONFIG_PROPERTIES_FILE);
        String url = "jdbc:mysql://localhost:3306/buber_db?autoReconnect=true&useSSL=false";
        String login = "root";
        String password = "root";
        String poolSize = "5";

        Assert.assertEquals(url, propertyFileManager.getValue(PropertyKey.DB_URL));
        Assert.assertEquals(login, propertyFileManager.getValue(PropertyKey.DB_USER));
        Assert.assertEquals(password, propertyFileManager.getValue(PropertyKey.DB_PASSWORD));
        Assert.assertEquals(poolSize, propertyFileManager.getValue(PropertyKey.DB_POOL_SIZE));
    }

    @Test
    public void testGenerateLocationMethod(){
        RandomGenerator randomLocationGenerator = new RandomGenerator();
        Assert.assertNotNull(randomLocationGenerator.generate());
    }
}
