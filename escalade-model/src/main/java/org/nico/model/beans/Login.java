package org.nico.model.beans;

public class Login {

    //====  ATTRIBUTES  ====

    private String email;
    private String password;

    //====  CONSTRUCTOR  ====//

    public Login() {}

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //====  GETTERS AND SETTERS  ====//


    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }


    /**
     * method to string Login
     * @return email
     * @return password
     */
    @Override
    public String toString() {
        return "email : " + email + "password : " + password;
    }
}
