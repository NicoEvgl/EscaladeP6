package org.nico.business.contract.manager;

import org.nico.model.beans.User;

import java.util.List;


public interface UserManager {
    void createUser(User user);
    List<User> findUserList();
    User findUser(Integer id);
    User findUserByAttribute(String attribute, Object attributeValue);
    void updateUser(User user);
    void deleteUser(Integer id);
}
