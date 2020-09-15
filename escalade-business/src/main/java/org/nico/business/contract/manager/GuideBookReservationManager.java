package org.nico.business.contract.manager;

import org.nico.model.beans.GuideBookReservation;

public interface GuideBookReservationManager {
    void createGuideBookReservation(GuideBookReservation guideBookReservation);
    GuideBookReservation findGuideBookReservationByGuideBookAndUserId(Integer guideBookId, Integer userId);
}
