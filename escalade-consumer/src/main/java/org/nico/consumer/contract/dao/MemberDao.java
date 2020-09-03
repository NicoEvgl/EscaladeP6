package org.nico.consumer.contract.dao;

import org.nico.model.beans.Member;

import java.util.List;

public interface MemberDao {

    void createMember(Member member);
    List<Member> findMemberList();
    void updateMember(Member member);

    void deleteMember(Integer id);
}
