package org.nico.consumer.contract;

import org.nico.consumer.contract.dao.ClimbingSiteDao;
import org.nico.consumer.contract.dao.UserDao;

public interface DaoFactory {

    UserDao getUserDao();
    void setUserDao(UserDao userDao);

    ClimbingSiteDao getClimbingSiteDao();
    void setClimbingSiteDao(ClimbingSiteDao climbingSiteDao);
}
