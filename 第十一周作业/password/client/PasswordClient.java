package password.client;

import password.PasswordEncryptionStrategy;
import password.PasswordValidation;
import password.md5.MD5Strategy;
import password.rsa.RSAStrategy;
import password.util.PasswordUtil;

public class PasswordClient {
    public static void main(String[] args) {
        RSAStrategy rsaStrategy = new RSAStrategy();
        MD5Strategy md5Strategy = new MD5Strategy();

        PasswordValidation rsa = new PasswordValidation(rsaStrategy);
        PasswordValidation md5 = new PasswordValidation(md5Strategy);

        System.out.println(rsa.checkPW("xiaoming", "abc",
                PasswordUtil.encryptLoginPassword("xiaoming","abc", rsaStrategy)) == true ? "RSA密码验证成功！": "RSA密码验证失败");
        System.out.println(md5.checkPW("xiaoming", "abc",
                PasswordUtil.encryptLoginPassword("xiaoming","abc", md5Strategy)) == true ? "MD5密码验证成功！": "MD5密码验证失败");
    }
}
