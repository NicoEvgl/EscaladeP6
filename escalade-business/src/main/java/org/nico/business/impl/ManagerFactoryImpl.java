package org.nico.business.impl;

import org.nico.business.contract.ManagerFactory;
import org.nico.business.contract.manager.MemberManager;

public class ManagerFactoryImpl implements ManagerFactory {


    private MemberManager memberManager;

    @Override
    public MemberManager getMemberManager() { return memberManager; }

    @Override
    public void setMemberManager(MemberManager memberManager) { this.memberManager = memberManager; }
}
