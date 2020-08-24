package password.client;

import password.PasswordValidation;
import password.encryption.MD5Strategy;
import password.encryption.RSAStrategy;

public class PasswordClient {
    public static void main(String[] args) {
        RSAStrategy rsaStrategy = new RSAStrategy();
        MD5Strategy md5Strategy = new MD5Strategy();

        PasswordValidation rsa = new PasswordValidation(rsaStrategy);
        PasswordValidation md5 = new PasswordValidation(md5Strategy);

        System.out.println(rsa.checkPW("xiaoming", "abc",
                "36142314293157819477107582480124862344585361016228459088624862768536779469751485797550394760297009881979254418867455774485603138737622558897982232579118679112525420095931668623538856779463764497093125935423687574892857993821680362999622225001263573099950305147384657170907364267468680760670633569462601696257") == true ? "RSA密码验证成功！": "RSA密码验证失败");
        System.out.println(md5.checkPW("xiaoming", "abc",
                "895340303c0b24b684f4bc44de2d342e") == true ? "MD5密码验证成功！": "MD5密码验证失败");
    }
}
