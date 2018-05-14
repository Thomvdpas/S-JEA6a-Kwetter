package main.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.jasypt.util.text.StrongTextEncryptor;

/**
 * @author Thom van de Pas on 19-3-2018
 */
public final class EncryptionHelper {

    private static final String KEY = "5cFeEc2A3E7Aa76C";

    private EncryptionHelper() {
    }

    public static String encryptString(String data) {
        String saltedData = saltData(data);
        return encryptData(saltedData);
    }

    public static String encryptPassword(String username, String password) {
        String saltedPassword = saltData(username + password);
        return encryptData(saltedPassword);
    }

    public static String getEncryptedPassword(String password) {
        return encryptData(password);
    }

    public static String encryptData(String data) {
        return DigestUtils.sha256Hex(data);
    }

    public static String encryptReversible(String value) {
        return getEncryptor().encrypt(value);
    }

    public static String decryptReversible(String value) {
        return getEncryptor().decrypt(value);
    }

    private static StrongTextEncryptor getEncryptor() {
        StrongTextEncryptor encryptor = new StrongTextEncryptor();
        encryptor.setPassword(KEY);

        return encryptor;
    }


    private static String saltData(String data) {
        return "Q4R@d8Lb2UP-qts%ndnVh_G7N-" + data;
    }
}
