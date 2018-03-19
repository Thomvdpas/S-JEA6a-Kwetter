package main.util;

import org.jasypt.util.text.StrongTextEncryptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Thom van de Pas on 19-3-2018
 */
public final class EncryptionHelper {

    private static final String KEY = "5cFeEc2A3E7Aa76C";

    private EncryptionHelper() {
    }

    public static String encryptString(String data) {
        String saltedData = saltData(data);
        return encryptData(saltedData, true);
    }

    public static String encryptPassword(String username, String password) {
        String saltedPassword = saltData(username + password);
        return encryptData(saltedPassword, false);
    }

    private static String encryptData(String data, boolean url) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(data.getBytes());
            return url ? Base64.getUrlEncoder().encodeToString(bytes) : Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return data;
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
