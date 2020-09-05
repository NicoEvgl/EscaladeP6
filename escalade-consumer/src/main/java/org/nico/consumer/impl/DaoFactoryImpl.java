package org.nico.consumer.impl;

import org.nico.consumer.contract.DaoFactory;
import org.nico.consumer.contract.dao.UserDao;

public class DaoFactoryImpl extends AbstractDao implements DaoFactory {

    private UserDao userDao;

    @Override
    public UserDao getUserDao() { return userDao; }

    @Override
    public void setUserDao(UserDao userDao) { this.userDao = userDao; }
}
