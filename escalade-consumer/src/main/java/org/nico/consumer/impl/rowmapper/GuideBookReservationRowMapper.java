package org.nico.consumer.impl.rowmapper;

import org.nico.consumer.impl.dao.GuideBookDaoImpl;
import org.nico.consumer.impl.dao.UserDaoImpl;
import org.nico.model.beans.GuideBookReservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuideBookReservationRowMapper implements RowMapper<GuideBookReservation> {
    @Override
    public GuideBookReservation mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        GuideBookReservation guideBookReservation = new GuideBookReservation();
        GuideBookDaoImpl guideBookDao = new GuideBookDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();

        guideBookReservation.setId(resultSet.getInt("id"));
        guideBookReservation.setReservationStatus(resultSet.getString("reservation_status"));
        guideBookReservation.setGuideBook(guideBookDao.findGuideBook(resultSet.getInt("guideBook_id")));
        guideBookReservation.setUser(userDao.findUser(resultSet.getInt("user_id")));

        return guideBookReservation;
    }
}
