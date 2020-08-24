package password.util;

import password.PasswordEncryptionStrategy;

public class PasswordUtil {

    public static String encryptLoginPassword(String userId, String password, PasswordEncryptionStrategy encryption) {
        return encryption.encrypt(userId+password);
    }

}
