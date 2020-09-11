package org.nico.model.beans;

public class Sector {

    //====  ATTRIBUTES  ====//

    private Integer id;
    private String name;
    private String description;

    private ClimbingSite climbingSite;

    //====  CONSTRUCTOR  ====//

    /**
     * Default constructor
     */
    public Sector() {}

    /**
     *
     * @param id : sector's id
     * @param name : sector's name
     * @param description : sector's description
     * @param climbingSite : sector's climbingSite
     */
    public Sector(Integer id, String name, String description, ClimbingSite climbingSite){
        this.id = id;
        this.name = name;
        this.description = description;
        this.climbingSite = climbingSite;
    }

    //====  GETTERS AND SETTERS  ====//


    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public ClimbingSite getClimbingSite() { return climbingSite; }

    public void setClimbingSite(ClimbingSite climbingSite) { this.climbingSite = climbingSite; }
}
