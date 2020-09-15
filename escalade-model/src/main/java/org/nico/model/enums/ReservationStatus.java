package org.nico.model.enums;

public enum ReservationStatus {

    PENDING ("En attente"),
    REFUSED("Refusée"),
    CANCELLED("Annulée"),
    ACCEPTED ("Acceptée"),
    CLOSED ("Clôturée");

    private final String reservationStatusValue;

    //====  CONSTRUCTOR  ====

    ReservationStatus(String reservationStatusValue){
        this.reservationStatusValue = reservationStatusValue;
    }

    public String getReservationStatusValue(){
        return this.reservationStatusValue;
    }
}
