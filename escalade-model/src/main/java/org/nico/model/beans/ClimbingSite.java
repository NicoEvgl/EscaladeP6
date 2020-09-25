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
    private String quotationMin;
    private String quotationMax;
    private String info;
    private boolean certified;

    private User user;
    private List<Photo> photoList;
    private List<Sector> sectorList;
    private List<Comment> commentList;

    //====  CONSTRUCTOR  ====//

    public ClimbingSite() {}

    public ClimbingSite(Integer id, String name, String region, String climbingType, String rockType, String height, Integer nbRoutes, String quotationMin, String quotationMax, String info, boolean certified, User user, List<Photo> photoList, List<Sector> sectorList, List<Comment> commentList) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.climbingType = climbingType;
        this.rockType = rockType;
        this.height = height;
        this.nbRoutes = nbRoutes;
        this.quotationMin = quotationMin;
        this.quotationMax = quotationMax;
        this.info = info;
        this.user = user;
        this.photoList = photoList;
        this.sectorList = sectorList;
        this.commentList = commentList;
        this.certified = certified;
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

    public String getQuotationMin() { return quotationMin; }

    public void setQuotationMin(String quotationMin) { this.quotationMin = quotationMin; }

    public String getQuotationMax() { return quotationMax; }

    public void setQuotationMax(String quotationMax) { this.quotationMax = quotationMax; }

    public String getInfo() { return info; }

    public void setInfo(String info) { this.info = info; }

    public boolean isCertified() { return this.certified; }

    public void setCertified(boolean certified) { this.certified = certified; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public List<Photo> getPhotoList() { return photoList; }

    public void setPhotoList(List<Photo> photoList) { this.photoList = photoList; }

    public List<Sector> getSectorList() { return sectorList; }

    public void setSectorList(List<Sector> sectorList) { this.sectorList = sectorList; }

    public List<Comment> getCommentList() { return commentList; }

    public void setCommentList(List<Comment> commentList) { this.commentList = commentList; }

}

