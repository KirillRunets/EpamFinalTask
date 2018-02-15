package by.runets.buber.infrastructure.util;

import by.runets.buber.infrastructure.exception.EncryptorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncrypt {
    private final static Logger LOGGER = LogManager.getLogger(PasswordEncrypt.class);
    private final static String ENCRYPTOR = "MD5";
    public static String encryptPassword(String password) {
        String md5 = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(ENCRYPTOR);
            digest.update(password.getBytes(), 0, password.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.fatal(e);
            throw new RuntimeException(e);
        }
        return md5;
    }
}
