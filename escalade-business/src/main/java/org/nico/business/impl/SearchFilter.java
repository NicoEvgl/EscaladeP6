package org.nico.business.impl;

import javax.inject.Named;

@Named("searchFilter")
public class SearchFilter {

    //====  ATTRIBUTES  ====//

    private String name;
    private String region;
    private Integer nbRoutes;
    private String quotation;

    //====  CONSTRUCTOR  ====//

    /**
     * default constructor
     */
    public SearchFilter(){

    }

    /**
     * constructor
     * @param name
     * @param region
     * @param nbRoutes
     * @param quotation
     */
    public SearchFilter(String name, String region, Integer nbRoutes, String quotation) {
        this.name = name;
        this.region = region;
        this.nbRoutes = nbRoutes;
        this.quotation = quotation;
    }

    //====  GETTERS AND SETTERS  ====//


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getNbRoutes() {
        return nbRoutes;
    }

    public void setNbRoutes(Integer nbRoutes) {
        this.nbRoutes = nbRoutes;
    }

    public String getQuotation() { return quotation; }

    public void setQuotation(String quotation) { this.quotation = quotation; }

    @Override
    public String toString() {
        return "SearchFilter = " + " name :'" + name + "', region :'" + region + "', nbRoutes :'" + nbRoutes + "', quotation :'" + quotation;
    }
}
