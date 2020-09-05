package org.nico.consumer.contract.dao;

import org.nico.model.beans.User;

import java.util.List;

public interface UserDao {

    void createUser(User user);
    List<User> findUserList();

    User findUser(Integer id);

    void updateUser(User user);
    void deleteUser(Integer id);
    User findUserByAttribute(String attribute, Object attributeValue);
}
