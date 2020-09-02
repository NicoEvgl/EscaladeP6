package org.nico.consumer.contract;

import org.nico.consumer.contract.dao.MemberDao;

public interface DaoFactory {

    MemberDao getMemberDao();
    void setMemberDao(MemberDao memberDao);
}
