package org.nico.business.impl;

import org.nico.business.contract.ManagerFactory;
import org.nico.business.contract.manager.UserManager;

public class ManagerFactoryImpl implements ManagerFactory {


    private UserManager userManager;

    @Override
    public UserManager getUserManager() { return userManager; }

    @Override
    public void setUserManager(UserManager userManager) { this.userManager = userManager; }
}
