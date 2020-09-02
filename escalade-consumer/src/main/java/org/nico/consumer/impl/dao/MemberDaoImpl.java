package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.MemberDao;
import org.nico.consumer.impl.AbstractDaoImpl;
import org.nico.consumer.impl.rowmapper.MemberRowMapper;
import org.nico.model.beans.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.Types;
import java.util.List;

public class MemberDaoImpl extends AbstractDaoImpl implements MemberDao {

    @Override
    public void createMember(Member member) {
        String sql = "INSERT INTO public.member (gender, first_name, last_name, username, email, password, address, address2, zip, city, role)"
                + "VALUES (:gender, :firstName, :lastName, :username, :email, :password, :address, :address2, :zip, :city, :role)";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("gender", member.getGender(), Types.VARCHAR);
        mapSqlParameterSource.addValue("firstName", member.getFirstName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("lastName", member.getLastName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("username", member.getUsername(), Types.VARCHAR);
        mapSqlParameterSource.addValue("email", member.getEmail(), Types.VARCHAR);
        mapSqlParameterSource.addValue("password", member.getPassword(), Types.VARCHAR);
        mapSqlParameterSource.addValue("role", member.getRole(), Types.VARCHAR);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public List<Member> findMemberList() {
        String sql = "SELECT * FROM public.member";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourceEscalade());
        MemberRowMapper memberRowMapper = new MemberRowMapper();
        List<Member> memberList = jdbcTemplate.query(sql, memberRowMapper);

        return memberList;
    }

    @Override
    public void updateMember(Member member) {
        String sql = "UPDATE public.member SET "
                + "gender = :gender, "
                + "first_name = :firstName, "
                + "last_name = :lastName, "
                + "username = :username, "
                + "email = :email, "
                + "password = :password, "
                + "role = :role, "
                + "WHERE id = :id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(member);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());

        namedParameterJdbcTemplate.update(sql, sqlParameterSource);

    }
}
