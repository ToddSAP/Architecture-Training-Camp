import windows.*;

public class Client {
    public static void main(String[] args) {
        WindowForm winForm = new WindowForm("Window窗口");
        Picture logo = new Picture("LOGO图片");
        Button login = new Button("登录按钮");
        Button registration = new Button("注册按钮");
        Frame frame = new Frame("frame1");
        Label userName = new Label("用户名");
        TextBox textBox = new TextBox("文本框");
        Label password = new Label("密码");
        PasswordBox passwordBox = new PasswordBox("密码框");
        CheckBox checkBox = new CheckBox("复选框");
        TextBox rememberName = new TextBox("记住用户名");
        LinkLabel forgotPass = new LinkLabel("忘记密码");

        winForm.addChild(logo);
        winForm.addChild(login);
        winForm.addChild(registration);
        winForm.addChild(frame);

        frame.addChild(userName);
        frame.addChild(textBox);
        frame.addChild(password);
        frame.addChild(passwordBox);
        frame.addChild(checkBox);
        frame.addChild(rememberName);
        frame.addChild(forgotPass);

        winForm.operation();
    }
}
