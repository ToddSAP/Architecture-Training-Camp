package password;

import password.util.PasswordUtil;

public class PasswordValidation {
    private PasswordEncryptionStrategy encryption;

    public PasswordValidation (PasswordEncryptionStrategy encryption) {
        this.encryption = encryption;
    }

    public boolean checkPW (String userId, String password, String encryptedPassword) {
        return PasswordUtil.encryptLoginPassword(userId, password, encryption).
                equals(encryptedPassword);
    }
}


