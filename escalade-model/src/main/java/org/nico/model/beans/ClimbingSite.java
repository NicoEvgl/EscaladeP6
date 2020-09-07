package org.nico.model.beans;


import java.util.List;

public class ClimbingSite {

    //====  ATTRIBUTES  ====//

    private Integer id;
    private String name;
    private String region;
    private String climbingType;
    private String rockType;
    private String height;
    private Integer nbRoutes;
    private String cotation;
    private String info;

    private User user;
    private List<Photo> photoList;

    //====  CONSTRUCTOR  ====//

    public ClimbingSite() {}

    public ClimbingSite(Integer id, String name, String region, String climbingType, String rockType, String height, Integer nbRoutes, String cotation, String info, User user, List<Photo> photoList) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.climbingType = climbingType;
        this.rockType = rockType;
        this.height = height;
        this.nbRoutes = nbRoutes;
        this.cotation = cotation;
        this.info = info;
        this.user = user;
        this.photoList = photoList;
    }

    //====  GETTERS AND SETTERS  ====//


    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getRegion() { return region; }

    public void setRegion(String region) { this.region = region; }

    public String getClimbingType() { return climbingType; }

    public void setClimbingType(String climbingType) { this.climbingType = climbingType; }

    public String getRockType() { return rockType; }

    public void setRockType(String rockType) { this.rockType = rockType; }

    public String getHeight() { return height; }

    public void setHeight(String height) { this.height = height; }

    public Integer getNbRoutes() { return nbRoutes; }

    public void setNbRoutes(Integer nbRoutes) { this.nbRoutes = nbRoutes; }

    public String getCotation() { return cotation; }

    public void setCotation(String cotation) { this.cotation = cotation; }

    public String getInfo() { return info; }

    public void setInfo(String info) { this.info = info; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public List<Photo> getPhotoList() { return photoList; }

    public void setPhotoList(List<Photo> photoList) { this.photoList = photoList; }

    /**
     * method to String climbing sites
     * @return id,name,region,climbingType,rockType,height,nbRoutes,orientation,cotation
     */
    @Override
    public String toString(){
        return "Site id : " + id + "Nom : " + name + " Région : " + region + " Type d'escalade : " + climbingType + " Type de roche : " + rockType +
                " Hauteur : " + height + " Nombre de voies : " + nbRoutes + " Cotation : " + cotation + "User id = " + user.getId() ;
    }
}

