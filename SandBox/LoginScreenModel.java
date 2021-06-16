package SandBox;

public class LoginScreenModel {
    String login = "login";/*login == database login, password follows.*/
    String password = "password";

    public LoginScreenModel() {
        this.login = login;
        this.password = password;
    }

    public LoginScreenModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
