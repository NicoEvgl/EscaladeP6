package org.nico.model.enums;

public enum Role {

    ADMIN("Admin"), MEMBER("Membre"), USER("Utilisateur");

    private final String param;

    private Role(String param){
        this.param = param;
    }

    public String getParam(){
        return this.param;
    }
}
