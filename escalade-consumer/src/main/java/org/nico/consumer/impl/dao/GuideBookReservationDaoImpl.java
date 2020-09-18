package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.GuideBookReservationDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.consumer.impl.rowmapper.GuideBookReservationRowMapper;
import org.nico.model.beans.GuideBookReservation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;
import java.util.List;

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

    @Override
    public List<GuideBookReservation> findGuideBookReservationRequestList(Integer id) {
        String sql = "SELECT guidebook_reservation. * FROM public.guidebook_reservation " +
                "JOIN public.guidebook ON guidebook.id = guidebook_reservation.guidebook_id " +
                "WHERE guidebook.user_id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id);

        GuideBookReservationRowMapper guideBookReservationRowMapper = new GuideBookReservationRowMapper();
        List<GuideBookReservation> guideBookReservationList = namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, guideBookReservationRowMapper);

        return guideBookReservationList;
    }

    @Override
    public List<GuideBookReservation> findGuideBookReservationListByUserId(Integer id) {
        String sql = "SELECT * FROM public.guidebook_reservation WHERE user_id = "+ id;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourceEscalade());
        GuideBookReservationRowMapper guideBookReservationRowMapper = new GuideBookReservationRowMapper();
        List<GuideBookReservation> guideBookReservationList = jdbcTemplate.query(sql, guideBookReservationRowMapper);

        return guideBookReservationList;
    }

    @Override
    public GuideBookReservation findGuideBookReservationById(Integer id) {
        String sql = "SELECT * FROM public.guidebook_reservation WHERE id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id, Types.INTEGER);

        GuideBookReservationRowMapper guideBookReservationRowMapper = new GuideBookReservationRowMapper();
        GuideBookReservation guideBookReservation = namedParameterJdbcTemplate.queryForObject(sql, mapSqlParameterSource, guideBookReservationRowMapper);

        return guideBookReservation;
    }

    @Override
    public void updateGuideBookReservation(GuideBookReservation guideBookReservation) {
        String sql = "UPDATE public.guidebook_reservation SET "
                + "reservation_status = :reservationStatus, "
                + "guidebook_id = :guidebookId, "
                + "user_id = :userId "
                + "WHERE id = :id";

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", guideBookReservation.getId(), Types.INTEGER);
        mapSqlParameterSource.addValue("reservationStatus", guideBookReservation.getReservationStatus(), Types.VARCHAR);
        mapSqlParameterSource.addValue("guidebookId", guideBookReservation.getGuideBook().getId(), Types.INTEGER);
        mapSqlParameterSource.addValue("userId", guideBookReservation.getUser().getId(), Types.INTEGER);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public void deleteGuideBookReservation(Integer id) {
        String sql = "DELETE FROM public.guidebook_reservation WHERE id = :id";
        System.out.println(sql);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }
}
