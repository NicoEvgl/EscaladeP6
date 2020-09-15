package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.GuideBookReservationDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.consumer.impl.rowmapper.GuideBookReservationRowMapper;
import org.nico.model.beans.GuideBookReservation;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Override
    public GuideBookReservation findGuideBookReservationByGuideBookAndUserId(Integer guideBookId, Integer userId) {
        String sql = "SELECT * FROM public.guidebook_reservation WHERE guidebook_id = :guidebookId AND user_id = :userId";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("guidebookId", guideBookId, Types.INTEGER);
        mapSqlParameterSource.addValue("userId", userId, Types.INTEGER);
        GuideBookReservationRowMapper guideBookReservationRowMapper = new GuideBookReservationRowMapper();
        try {
            GuideBookReservation guideBookReservation = namedParameterJdbcTemplate.queryForObject(sql, mapSqlParameterSource, guideBookReservationRowMapper);
            return guideBookReservation;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
