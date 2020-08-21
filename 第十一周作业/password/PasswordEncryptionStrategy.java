package password;

public interface PasswordEncryptionStrategy {
    String encrypt (String str);

    String decrypt (String password);
}
