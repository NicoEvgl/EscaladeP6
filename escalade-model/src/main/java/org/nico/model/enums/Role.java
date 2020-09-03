package org.nico.model.enums;

public enum Role {

    ADMIN("Administrator"), MEMBER("Member"), USER("User");

    private final String param;

    private Role(String param){
        this.param = param;
    }

    public String getParam(){
        return this.param;
    }
}
