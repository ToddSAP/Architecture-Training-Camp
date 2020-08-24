package password;

public class PasswordValidation {
    private PasswordEncryptionStrategy encryption;

    public PasswordValidation (PasswordEncryptionStrategy encryption) {
        this.encryption = encryption;
    }

    public boolean checkPW (String userId, String password, String encryptedPassword) {
        return encryption.encrypt(userId+password).equals(encryptedPassword);
    }
}


