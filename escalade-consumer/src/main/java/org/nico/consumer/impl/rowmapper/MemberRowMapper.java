package org.nico.consumer.impl.rowmapper;

import org.nico.model.beans.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Member member = new Member();

        member.setGender(resultSet.getString("gender"));
        member.setId(resultSet.getInt("id"));
        member.setFirstName(resultSet.getString("first_name"));
        member.setLastName(resultSet.getString("last_name"));
        member.setUsername(resultSet.getString("username"));
        member.setEmail(resultSet.getString("email"));
        member.setPassword(resultSet.getString("password"));
        member.setRole(resultSet.getString("role"));

        return member;
    }
}