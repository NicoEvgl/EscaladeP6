package org.nico.business.contract.manager;

import org.nico.model.beans.User;
import org.nico.model.exception.UserBlockedException;

import java.util.List;


public interface UserManager {
    void createUser(User user);
    List<User> findUserList();
    User findUser(Integer id);
    User findUserByAttribute(String attribute, Object attributeValue) throws UserBlockedException;
    void updateUser(User user);
    void deleteUser(Integer id);
}
