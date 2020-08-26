package org.nico.model.beans;

public class Login {

    private String email;
    private String password;

    public Login() {}

    //====  GETTERS AND SETTERS  ====


    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "email : " + email + "password : " + password;
    }
}
