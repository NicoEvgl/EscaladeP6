package org.nico.business.contract;

import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.contract.manager.UserManager;

public interface ManagerFactory {

    UserManager getUserManager();
    void setUserManager(UserManager userManager);

    ClimbingSiteManager getClimbingSiteManager();
    void setClimbingSiteManager(ClimbingSiteManager climbingSiteManager);
}
