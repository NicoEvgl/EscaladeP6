package org.nico.consumer.contract;

import org.nico.consumer.contract.dao.*;

public interface DaoFactory {

    UserDao getUserDao();
    void setUserDao(UserDao userDao);

    ClimbingSiteDao getClimbingSiteDao();
    void setClimbingSiteDao(ClimbingSiteDao climbingSiteDao);

    PhotoDao getPhotoDao();
    void setPhotoDao(PhotoDao photoDao);

    SectorDao getSectorDao();
    void setSectorDao(SectorDao sectorDao);

    RouteDao getRouteDao();

    void setRouteDao(RouteDao routeDao);
}
