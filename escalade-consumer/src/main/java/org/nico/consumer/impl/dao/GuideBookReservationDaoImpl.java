package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.GuideBookReservationDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.model.beans.GuideBookReservation;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;

public class GuideBookReservationDaoImpl extends AbstractDao implements GuideBookReservationDao {

    @Override
    public void createGuideBookReservation(GuideBookReservation guideBookReservation) {
        String sql = "INSERT INTO public.guidebook_reservation (reservation_status, guidebook_id, user_id)"
                + "VALUES (:reservationStatus, :guidebookId, :userId)";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("reservationStatus", guideBookReservation.getReservationStatus(), Types.VARCHAR);
        mapSqlParameterSource.addValue("guidebookId", guideBookReservation.getGuideBook().getId(), Types.INTEGER);
        mapSqlParameterSource.addValue("userId", guideBookReservation.getUser().getId(), Types.INTEGER);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }
}
