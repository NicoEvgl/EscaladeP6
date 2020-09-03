package org.nico.business.contract.manager;

import org.nico.model.beans.Member;

import java.util.List;

public interface MemberManager {
    void createMember(Member member);
    List<Member> findMemberList();
    void updateMember(Member member);
    void deleteMember(Integer id);
    Member findMemberByUsername(String username, Object value);
}
