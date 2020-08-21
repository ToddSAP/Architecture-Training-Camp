package password.rsa;

import password.PasswordEncryptionStrategy;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAStrategy implements PasswordEncryptionStrategy {
    private static RSAPublicKey publicKey;
    private static RSAPrivateKey privateKey;

    private boolean checkPublicKey() {
        if (publicKey == null || privateKey == null) {
            try {
                // 为了实现简便，直接每次对key赋值。现实中，需要将key保存在文件中，publicKey传输给加密方，privateKey要自己保存好。
                RSA.generateKey();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return publicKey != null && privateKey != null;
    }

    @Override
    public String encrypt(String str) {
        String encryptedPassword = null;
        try {
            if (!checkPublicKey()) throw new KeyException("public or private key is null");
            byte[] passwordText = str.getBytes("UTF-8");
            BigInteger m = new BigInteger(passwordText);
            BigInteger c = m.modPow(publicKey.getPublicExponent(), publicKey.getModulus());
            encryptedPassword = c.toString();
            System.out.println("RSA加密密码："+encryptedPassword);
        } catch (UnsupportedEncodingException | KeyException e) {
            e.printStackTrace();
        }
        return encryptedPassword;
    }

    @Override
    public String decrypt(String encryptedPassword) {
        String password = null;
        try {
            if (!checkPublicKey()) throw new KeyException("public or private key is null");
            BigInteger c = new BigInteger(encryptedPassword);
            BigInteger m = c.modPow(privateKey.getPrivateExponent(), privateKey.getModulus());
            password = m.toString();
        } catch (KeyException e) {
            e.printStackTrace();
        }
        return password;
    }

    private final static class RSA {
        static void generateKey() throws NoSuchAlgorithmException {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair keyPair = generator.generateKeyPair();
            publicKey = (RSAPublicKey) keyPair.getPublic();
            privateKey = (RSAPrivateKey) keyPair.getPrivate();
        }
    }
}
