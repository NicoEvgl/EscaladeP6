package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.MemberDao;
import org.nico.consumer.impl.AbstractDaoImpl;
import org.nico.consumer.impl.rowmapper.MemberRowMapper;
import org.nico.model.beans.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;
import java.util.List;

public class MemberDaoImpl extends AbstractDaoImpl implements MemberDao {

    @Override
    public void createMember(Member member) {
        String sql = "INSERT INTO public.member (gender, first_name, last_name, username, email, password, address, address2, zip, city, role)"
                + "VALUES (:gender, :firstName, :lastName, :username, :email, :password, :address, :address2, :zip, :city, :role)";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("gender", member.getGender(), Types.VARCHAR);
        parameterSource.addValue("firstName", member.getFirstName(), Types.VARCHAR);
        parameterSource.addValue("lastName", member.getLastName(), Types.VARCHAR);
        parameterSource.addValue("username", member.getUsername(), Types.VARCHAR);
        parameterSource.addValue("email", member.getEmail(), Types.VARCHAR);
        parameterSource.addValue("password", member.getPassword(), Types.VARCHAR);
        parameterSource.addValue("role", member.getRole(), Types.VARCHAR);

        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public List<Member> findMemberList() {
        String sql = "SELECT * FROM public.member";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourceEscalade());
        MemberRowMapper memberRowMapper = new MemberRowMapper();
        List<Member> memberList = jdbcTemplate.query(sql, memberRowMapper);

        return memberList;
    }
}
