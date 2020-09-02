package org.nico.business.contract;

import org.nico.business.contract.manager.MemberManager;

public interface ManagerFactory {

    MemberManager getMemberManager();
    void setMemberManager(MemberManager memberManager);
}
