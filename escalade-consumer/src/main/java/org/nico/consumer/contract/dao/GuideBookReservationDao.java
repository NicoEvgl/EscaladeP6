package org.nico.consumer.contract.dao;

import org.nico.model.beans.GuideBookReservation;

import java.util.List;

public interface GuideBookReservationDao {
    void createGuideBookReservation(GuideBookReservation guideBookReservation);
    GuideBookReservation findGuideBookReservationByGuideBookAndUserId(Integer guideBookId, Integer userId);
    List<GuideBookReservation> findGuideBookReservationRequestList(Integer id);
    List<GuideBookReservation> findGuideBookReservationListByUserId(Integer id);
}
