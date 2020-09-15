package org.nico.business.contract;

import org.nico.business.contract.manager.*;

public interface ManagerFactory {

    UserManager getUserManager();
    void setUserManager(UserManager userManager);

    ClimbingSiteManager getClimbingSiteManager();
    void setClimbingSiteManager(ClimbingSiteManager climbingSiteManager);

    PhotoManager getPhotoManager();
    void setPhotoManager(PhotoManager photoManager);

    SectorManager getSectorManager();
    void setSectorManager(SectorManager sectorManager);

    RouteManager getRouteManager();
    void setRouteManager(RouteManager routeManager);

    CommentManager getCommentManager();
    void setCommentManager(CommentManager commentManager);

    GuideBookManager getGuideBookManager();
    void setGuideBookManager(GuideBookManager guideBookManager);

    GuideBookReservationManager getGuideBookReservationManager();
    void setGuideBookReservationManager(GuideBookReservationManager guideBookReservationManager);
}
