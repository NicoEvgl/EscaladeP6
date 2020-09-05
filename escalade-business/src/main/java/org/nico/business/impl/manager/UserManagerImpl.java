package org.nico.business.impl.manager;

import org.nico.business.contract.manager.UserManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.beans.User;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;


public class UserManagerImpl extends AbstractManager implements UserManager {

    @Override
    public void createUser(User user) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getUserDao().createUser(user);
            }
        });
    }

    @Override
    public List<User> findUserList() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        List<User> userList = transactionTemplate.execute(transactionStatus -> {
            List<User> userListTxes = new ArrayList<>();
            userListTxes = getDaoFactory().getUserDao().findUserList();
            return userListTxes;
        });
        return userList;
    }

    @Override
    public void updateUser(User user) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getUserDao().updateUser(user);
            }
        });

    }

    @Override
    public void deleteUser(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getUserDao().deleteUser(id);
            }
        });
    }

    @Override
    public User findUserByAttribute(String attribute, Object attributeValue) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        User user = transactionTemplate.execute(transactionStatus -> {
            User userTx = new User();
            userTx = getDaoFactory().getUserDao().findUserByAttribute(attribute, attributeValue);
            return userTx;
        });
        return user;
    }

}
