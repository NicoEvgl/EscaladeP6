package org.nico.model.enums;

public enum ReservationStatus {

    PENDING ("En attente"),
    REFUSED("Refusée"),
    CANCELLED("Annulée"),
    ACCEPTED ("Acceptée"),
    CLOSED ("Clôturée");

    private final String statusValue;

    //====  CONSTRUCTOR  ====

    ReservationStatus(String statusValue){
        this.statusValue = statusValue;
    }

    public String getStatusValue(){
        return this.statusValue;
    }
}
