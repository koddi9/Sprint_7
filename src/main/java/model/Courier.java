package model;

public class Courier {
    int id;
    String login;
    String password;
    String firstName;

    public Courier() {
    }

    public Courier(int id) {
        this.id = id;
    }

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public Courier(int id, String login, String password, String firstName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
