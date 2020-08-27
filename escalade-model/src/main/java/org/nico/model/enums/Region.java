package org.nico.model.enums;

public enum Region {

    AUVERGNE_RHONE_ALPES("Auvergne-Rhône-Alpes"),
    BOURGOGNE_FRANCHE_COMPTE("Bourgogne-Franche-Comté"),
    BRETAGNE("Bretagne"),
    CENTRE_VAL_DE_LOIRE("Centre-Val-de-Loire"),
    CORSE("Corse"),
    GRAND_EST("Grand-Est"),
    GUYANNE("Guyane"),
    HAUTS_DE_FRANCE("Hauts-de-France"),
    ILE_DE_FRANCE("Ile-de-France"),
    NORMANDIE("Normandie"),
    NOUVELLE_AQUITAINE("Nouvelle-Aquitaine"),
    OCCITANIE("Occitanie"),
    PAYS_DE_LA_LOIRE("Pays-de-la-Loire"),
    PACA("Paca"),
    REUNION("Réunion");

    private final String param;

    Region(String param) {
        this.param = param;
    }

    public String getParam() { return this.param; }
}
