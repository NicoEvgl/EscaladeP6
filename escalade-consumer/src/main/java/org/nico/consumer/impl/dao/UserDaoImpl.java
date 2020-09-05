package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.UserDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.consumer.impl.rowmapper.UserRowMapper;
import org.nico.model.beans.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO public.user (gender, first_name, last_name, username, email, password, address, address2, zip, city, role)"
                + "VALUES (:gender, :firstName, :lastName, :username, :email, :password, :address, :address2, :zip, :city, :role)";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("gender", user.getGender(), Types.VARCHAR);
        mapSqlParameterSource.addValue("firstName", user.getFirstName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("lastName", user.getLastName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("username", user.getUsername(), Types.VARCHAR);
        mapSqlParameterSource.addValue("email", user.getEmail(), Types.VARCHAR);
        mapSqlParameterSource.addValue("password", user.getPassword(), Types.VARCHAR);
        mapSqlParameterSource.addValue("address", user.getAddress(), Types.VARCHAR);
        mapSqlParameterSource.addValue("address2", user.getAddress2(), Types.VARCHAR);
        mapSqlParameterSource.addValue("zip", user.getZip(), Types.VARCHAR);
        mapSqlParameterSource.addValue("city", user.getCity(), Types.VARCHAR);
        mapSqlParameterSource.addValue("role", user.getRole(), Types.VARCHAR);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public List<User> findUserList() {
        String sql = "SELECT * FROM public.user ORDER BY id";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourceEscalade());
        UserRowMapper userRowMapper = new UserRowMapper();
        List<User> userList = jdbcTemplate.query(sql, userRowMapper);

        return userList;
    }

    @Override
    public User findUser(Integer id) {
        String sql = "SELECT * FROM public.user WHERE id = :id";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id, Types.INTEGER);
        UserRowMapper userRowMapper = new UserRowMapper();
        User user = namedParameterJdbcTemplate.queryForObject(sql, mapSqlParameterSource, userRowMapper);

        return user;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE public.user SET "
                + "gender = :gender, "
                + "first_name = :firstName, "
                + "last_name = :lastName, "
                + "username = :username, "
                + "email = :email, "
                + "password = :password, "
                + "address = :address, "
                + "address2 = :address2, "
                + "city = :city, "
                + "zip = :zip, "
                + "role = :role, "
                + "WHERE id = :id";
        BeanPropertySqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        sqlParameterSource.registerSqlType("id", Types.INTEGER);
        sqlParameterSource.registerSqlType("gender", Types.VARCHAR);
        sqlParameterSource.registerSqlType("firstName", Types.VARCHAR);
        sqlParameterSource.registerSqlType("lastName", Types.VARCHAR);
        sqlParameterSource.registerSqlType("pseudo", Types.VARCHAR);
        sqlParameterSource.registerSqlType("email", Types.VARCHAR);
        sqlParameterSource.registerSqlType("password", Types.VARCHAR);
        sqlParameterSource.registerSqlType("address", Types.VARCHAR);
        sqlParameterSource.registerSqlType("address2", Types.VARCHAR);
        sqlParameterSource.registerSqlType("zip", Types.VARCHAR);
        sqlParameterSource.registerSqlType("city", Types.VARCHAR);
        sqlParameterSource.registerSqlType("role", Types.VARCHAR);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());

        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public void deleteUser(Integer id) {
        String sql = "DELETE FROM public.user WHERE id = :id";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public User findUserByAttribute(String attribute, Object attributeValue) {
        String sql = "SELECT * FROM public.user WHERE "+attribute+" = :"+attribute+"";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(attribute, attributeValue);
        User user = namedParameterJdbcTemplate.queryForObject(sql, mapSqlParameterSource, new UserRowMapper());

        return user;
    }
}
