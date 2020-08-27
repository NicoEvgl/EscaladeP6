package org.nico.model.beans;

import java.util.Date;

public class GuideBook {

    //====  ATTRIBUTES  ====//

    private Integer id;
    private String name;
    private String description;
    private String region;
    private Date releaseDate;
    private boolean isBooked;

    private User user;

    //====  CONSTRUCTOR  ====//

    public GuideBook(){}

    public GuideBook(Integer id, String name, String description, String region, Date releaseDate, boolean isBooked, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.region = region;
        this.releaseDate = releaseDate;
        this.isBooked = isBooked;
        this.user = user;
    }

    //====  GETTERS AND SETTERS  ====//


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Topo {" +
                "id : " + id +
                ", Nom : " + name + '\'' +
                ", Description :'" + description + '\'' +
                ", Région :'" + region + '\'' +
                ", Date de parution : " + releaseDate +
                ", Disponible : " + isBooked +
                ", Membre : " + user +
                '}';
    }
}
