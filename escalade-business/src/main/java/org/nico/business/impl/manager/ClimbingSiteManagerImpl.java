package org.nico.business.impl.manager;

import org.nico.business.contract.manager.ClimbingSiteManager;
import org.nico.business.impl.AbstractManager;
import org.nico.consumer.contract.dao.ClimbingSiteDao;
import org.nico.model.beans.ClimbingSite;
import org.nico.model.beans.User;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

public class ClimbingSiteManagerImpl extends AbstractManager implements ClimbingSiteManager {

    @Override
    public void createClimbingSite(ClimbingSite climbingSite){
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getClimbingSiteDao().createClimbingSite(climbingSite);
            }
        });
    }

    @Override
    public List<ClimbingSite> findClimbingSiteList() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        List<ClimbingSite> climbingSiteList = transactionTemplate.execute(transactionStatus -> {
            List<ClimbingSite> climbingSiteListTx = new ArrayList<>();
            climbingSiteListTx = getDaoFactory().getClimbingSiteDao().findClimbingSiteList();
            return climbingSiteListTx;
        });
        return climbingSiteList;
    }
}

