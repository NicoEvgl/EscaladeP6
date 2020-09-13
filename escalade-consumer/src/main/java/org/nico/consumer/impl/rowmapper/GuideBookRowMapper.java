package org.nico.consumer.impl.rowmapper;

import org.nico.consumer.impl.dao.UserDaoImpl;
import org.nico.model.beans.GuideBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuideBookRowMapper implements RowMapper<GuideBook> {

    @Override
    public GuideBook mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        GuideBook guideBook = new GuideBook();
        UserDaoImpl userDao = new UserDaoImpl();

        guideBook.setId(resultSet.getInt("id"));
        guideBook.setName(resultSet.getString("name"));
        guideBook.setDescription(resultSet.getString("description"));
        guideBook.setRegion(resultSet.getString("region"));
        guideBook.setReleaseDate(resultSet.getDate("release_date"));
        guideBook.setBooked(resultSet.getBoolean("is_booked"));
        guideBook.setUser(userDao.findUser(resultSet.getInt("user_id")));

        return guideBook;
    }
}
