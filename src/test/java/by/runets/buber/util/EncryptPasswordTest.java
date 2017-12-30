package by.runets.buber.util;

import by.runets.buber.infrastructure.exception.EncryptorException;
import by.runets.buber.infrastructure.util.PasswordEncrypt;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EncryptPasswordTest {
    @Test
    public void testEncryptPassword() throws EncryptorException {
        String expected = "pass";
        String actual = "1a1dc91c907325c69271ddf0c944bc72";
        Assert.assertEquals(actual, PasswordEncrypt.encryptPassword(expected));
    }
}
