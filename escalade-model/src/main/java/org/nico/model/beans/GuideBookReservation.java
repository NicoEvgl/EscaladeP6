package org.nico.model.beans;

public class GuideBookReservation {

    //====  ATTRIBUTES  ====//

    private Integer id;
    private String reservationStatus;

    private GuideBook guideBook;
    private User user;

    //====  CONSTRUCTOR  ====//

    public GuideBookReservation(){}

    public GuideBookReservation(Integer id, String reservationStatus, GuideBook guideBook, User user) {
        this.id = id;
        this.reservationStatus = reservationStatus;
        this.guideBook = guideBook;
        this.user = user;
    }

    //====  GETTERS AND SETTERS  ====//


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public GuideBook getGuideBook() {
        return guideBook;
    }

    public void setGuideBook(GuideBook guideBook) {
        this.guideBook = guideBook;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "GuideBookReservation : " + "id : " + id + " guideBook : " + guideBook + " user : " + user + " reservationStatus : '" + reservationStatus + " '";
    }
}
