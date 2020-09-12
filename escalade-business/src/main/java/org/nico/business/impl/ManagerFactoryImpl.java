package org.nico.business.impl;

import org.nico.business.contract.ManagerFactory;
import org.nico.business.contract.manager.*;

public class ManagerFactoryImpl implements ManagerFactory {


    private UserManager userManager;
    private ClimbingSiteManager climbingSiteManager;
    private PhotoManager photoManager;
    private SectorManager sectorManager;
    private RouteManager routeManager;

    @Override
    public UserManager getUserManager() { return userManager; }
    @Override
    public void setUserManager(UserManager userManager) { this.userManager = userManager; }

    @Override
    public ClimbingSiteManager getClimbingSiteManager() { return climbingSiteManager; }
    @Override
    public void setClimbingSiteManager(ClimbingSiteManager climbingSiteManager) { this.climbingSiteManager = climbingSiteManager; }

    @Override
    public PhotoManager getPhotoManager() {
        return photoManager;
    }
    @Override
    public void setPhotoManager(PhotoManager photoManager) {
        this.photoManager = photoManager;
    }

    @Override
    public SectorManager getSectorManager() { return sectorManager; }
    @Override
    public void setSectorManager(SectorManager sectorManager) { this.sectorManager = sectorManager; }

    @Override
    public RouteManager getRouteManager() { return routeManager; }
    @Override
    public void setRouteManager(RouteManager routeManager) { this.routeManager = routeManager; }
}

