package org.nico.model.beans;

public class User {

    //====  ATTRIBUTES  ====

    private Integer id;
    private String gender;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String address;
    private String address2;
    private String zip;
    private String city;
    private String role;

    //====  CONSTRUCTOR  ====

    public User() {
    }

    public User(Integer id, String gender, String firstName, String lastName, String username, String email,
                String password, String address, String address2, String zip, String city, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.address = address;
        this.address2 = address2;
        this.zip = zip;
        this.city= city;
        this.role= role;
    }

    //====  GETTERS AND SETTERS  ====

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //======= toString =======

    @Override
    public String toString() {
        return "User id : " + id + "Genre : " + gender + " Prénom : " + firstName + " Nom : " + lastName + " Nom d'utilisateur : " + username +
                " email : " + email + " mot de passe : " + password + "adresse : " + address + "code postal : " + zip + "Ville : " + city ;
    }
}
