package org.nico.business.impl.manager;

import org.nico.business.contract.manager.RouteManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.beans.Route;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;


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

    @Override
    public List<Route> findRouteBySector(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Route> routeList = transactionTemplate.execute(transactionStatus -> {
            List<Route> routeListTx = new ArrayList<>();
            routeListTx = getDaoFactory().getRouteDao().findRouteBySector(id);
            return  routeListTx;
        });

        return routeList;
    }

    @Override
    public Route findRoute(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        Route route = transactionTemplate.execute(transactionStatus -> {
            Route routeTx;
            routeTx = getDaoFactory().getRouteDao().findRoute(id);
            return routeTx;
        });

        return route;
    }

    @Override
    public void updateRoute(Route route) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getRouteDao().updateRoute(route);
            }
        });
    }

    @Override
    public void deleteRoute(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getRouteDao().deleteRoute(id);
            }
        });
    }
}
