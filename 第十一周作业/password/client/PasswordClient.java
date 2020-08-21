package password.client;

import password.PasswordValidation;
import password.rsa.RSAStrategy;
import password.util.PasswordUtil;

public class PasswordClient {
    public static void main(String[] args) {
        RSAStrategy rsaStrategy = new RSAStrategy();
        PasswordValidation passwordValidation = new PasswordValidation(rsaStrategy);
        System.out.println(passwordValidation.checkPW("xiaoming", "abc",
                PasswordUtil.encryptLoginPassword("xiaoming","abc", rsaStrategy)) == true ? "密码验证成功！": "密码验证失败");
    }
}
