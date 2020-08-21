package password.md5;

import password.PasswordEncryptionStrategy;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Strategy implements PasswordEncryptionStrategy {
    private static MessageDigest md5;

    @Override
    public String encrypt(String str) {
        String encryptedPassword = null;
        if (!checkIfMD5Initialized()) throw new ExceptionInInitializerError("MD5 can't initialized");
        try {
            md5.update(str.getBytes("UTF-8"));
            byte[] digest = md5.digest();
            int i;
            StringBuilder sb = new StringBuilder();
            for (int offset = 0; offset < digest.length; offset++) {
                i = digest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    sb.append(0);
                sb.append(Integer.toHexString(i));
            }
            //从下标0开始，length目的是截取多少长度的值
            encryptedPassword = sb.toString().substring(0, 32);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("MD5加密密码:"+encryptedPassword);
        return encryptedPassword;
    }

    @Override
    public String decrypt(String password) {
        throw new UnsupportedOperationException("MD5 can't be decrypted");
    }

    private boolean checkIfMD5Initialized () {
        if (md5 == null) {
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return md5 != null;
    }

}
