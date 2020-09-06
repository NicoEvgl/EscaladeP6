package org.nico.model.beans;


public class Photo {

    //====  ATTRIBUTES  ====//

    private Integer id;
    private String name;
    private String url;

    private ClimbingSite climbingSite;

    //====  CONSTRUCTOR  ====//

    /**
     * Default constructor
     */
    public Photo(){}

    /**
     * Constructor
     * @param id
     * @param name
     * @param url
     * @param climbingSite
     */
    public Photo(Integer id, String name, String url, ClimbingSite climbingSite) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.climbingSite = climbingSite;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ClimbingSite getClimbingSite() {
        return climbingSite;
    }

    public void setClimbingSite(ClimbingSite climbingSite) {
        this.climbingSite = climbingSite;
    }
}
