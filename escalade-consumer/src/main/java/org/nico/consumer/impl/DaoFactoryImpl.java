package org.nico.consumer.impl;

import org.nico.consumer.contract.DaoFactory;
import org.nico.consumer.contract.dao.MemberDao;

public class DaoFactoryImpl extends AbstractDao implements DaoFactory {

    private MemberDao memberDao;

    @Override
    public MemberDao getMemberDao() { return memberDao; }

    @Override
    public void setMemberDao(MemberDao memberDao) { this.memberDao = memberDao; }
}
