package org.nico.model.beans;

import org.nico.model.enums.Quotation;

public class Route {

    //====  ATTRIBUTES  ====//

    private Integer id;
    private String name;
    private String quotation;
    private String height;

    private Quotation enumQuotation;
    private Sector sector;

    //====  CONSTRUCTOR  ====//

    /**
     * Default constructor
     */
    public Route() {}

    /**
     * Constructor
     * @param id : route's id
     * @param name : route's name
     * @param quotation : route's quotation
     * @param height : route's height
     * @param sector : route's sector
     */
    public Route(Integer id, String name, String quotation, String height, Sector sector) {
        this.id = id;
        this.name = name;
        this.quotation = quotation;
        this.height = height;
        this.sector = sector;
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

    public String getQuotation() {
        return quotation;
    }

    public void setQuotation(String quotation) {
        this.quotation = quotation;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Quotation getEnumQuotation() {
        return enumQuotation;
    }

    public void setEnumQuotation(Quotation enumQuotation) {
        this.enumQuotation = enumQuotation;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
}
