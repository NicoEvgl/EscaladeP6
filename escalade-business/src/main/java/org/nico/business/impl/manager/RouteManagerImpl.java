package org.nico.business.impl.manager;

import org.nico.business.contract.manager.RouteManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.beans.Route;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class RouteManagerImpl extends AbstractManager implements RouteManager {
    @Override
    public void createRoute(Route route) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getRouteDao().createRoute(route);
            }
        });
    }
}
