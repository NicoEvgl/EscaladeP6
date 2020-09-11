package org.nico.model.beans;

public class Login {

    //====  ATTRIBUTES  ====//

    private String username;
    private String password;

    //====  CONSTRUCTOR  ====//

    public Login() {}

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //====  GETTERS AND SETTERS  ====//


    public String getUsername() { return username; }

    public void setUsername(String email) { this.username = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }


    /**
     * method to string Login
     * @return Login : pseudo + password
     */
    @Override
    public String toString() {
        return "Login = " + "pseudo : " + username + "password : " + password;
    }
}
