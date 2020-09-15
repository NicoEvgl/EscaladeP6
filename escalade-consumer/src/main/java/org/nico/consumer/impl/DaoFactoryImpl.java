package org.nico.consumer.impl;

import org.nico.consumer.contract.DaoFactory;
import org.nico.consumer.contract.dao.*;

public class DaoFactoryImpl implements DaoFactory {

    private UserDao userDao;
    private ClimbingSiteDao climbingSiteDao;
    private PhotoDao photoDao;
    private SectorDao sectorDao;
    private RouteDao routeDao;
    private CommentDao commentDao;
    private GuideBookDao guideBookDao;
    private GuideBookReservationDao guideBookReservationDao;

    @Override
    public UserDao getUserDao() { return userDao; }
    @Override
    public void setUserDao(UserDao userDao) { this.userDao = userDao; }

    @Override
    public ClimbingSiteDao getClimbingSiteDao() { return climbingSiteDao; }
    @Override
    public void setClimbingSiteDao(ClimbingSiteDao climbingSiteDao) { this.climbingSiteDao = climbingSiteDao; }

    @Override
    public PhotoDao getPhotoDao() { return photoDao; }
    @Override
    public void setPhotoDao(PhotoDao photoDao) { this.photoDao = photoDao; }

    @Override
    public SectorDao getSectorDao() { return sectorDao; }
    @Override
    public void setSectorDao(SectorDao sectorDao) { this.sectorDao = sectorDao; }

    @Override
    public RouteDao getRouteDao() { return routeDao; }
    @Override
    public void setRouteDao(RouteDao routeDao) { this.routeDao = routeDao; }

    @Override
    public CommentDao getCommentDao() { return commentDao; }
    @Override
    public void setCommentDao(CommentDao commentDao) { this.commentDao = commentDao; }

    @Override
    public GuideBookDao getGuideBookDao() { return guideBookDao; }
    @Override
    public void setGuideBookDao(GuideBookDao guideBookDao) { this.guideBookDao = guideBookDao; }

    @Override
    public GuideBookReservationDao getGuideBookReservationDao() { return guideBookReservationDao; }
    @Override
    public void setGuideBookReservationDao(GuideBookReservationDao guideBookReservationDao) { this.guideBookReservationDao = guideBookReservationDao; }
}

