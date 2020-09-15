package org.nico.consumer.contract.dao;

import org.nico.model.beans.GuideBookReservation;

public interface GuideBookReservationDao {
    void createGuideBookReservation(GuideBookReservation guideBookReservation);
    GuideBookReservation findGuideBookReservationByGuideBookAndUserId(Integer guideBookId, Integer userId);
}
