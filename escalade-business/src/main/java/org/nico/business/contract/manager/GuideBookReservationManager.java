package org.nico.business.contract.manager;

import org.nico.model.beans.GuideBookReservation;

import java.util.List;

public interface GuideBookReservationManager {
    void createGuideBookReservation(GuideBookReservation guideBookReservation);
    GuideBookReservation findGuideBookReservationByGuideBookAndUserId(Integer guideBookId, Integer userId);
    List<GuideBookReservation> findGuideBookReservationRequestList(Integer id);
    List<GuideBookReservation> findGuideBookReservationListByUserId(Integer id);
}
