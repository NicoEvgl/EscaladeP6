package org.nico.business.impl.manager;

import org.nico.business.contract.manager.SectorManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.beans.Sector;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

public class SectorManagerImpl extends AbstractManager implements SectorManager {

    @Override
    public void createSector(Sector sector) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getSectorDao().createSector(sector);
            }
        });
    }

    @Override
    public List<Sector> findSectorList() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Sector> sectorList = transactionTemplate.execute(transactionStatus -> {
            List<Sector> sectorListTx = new ArrayList<>();
            sectorListTx = getDaoFactory().getSectorDao().findSectorList();
            return  sectorListTx;
        });

        return sectorList;
    }

    @Override
    public List<Sector> findSectorByClimbingSite(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Sector> sectorList = transactionTemplate.execute(transactionStatus -> {
            List<Sector> sectorListTx = new ArrayList<>();
            sectorListTx = getDaoFactory().getSectorDao().findSectorByClimbingSite(id);
            return  sectorListTx;
        });

        return sectorList;
    }

    @Override
    public Sector findSector(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        Sector sector = transactionTemplate.execute(transactionStatus -> {
            Sector sectorTx;
            sectorTx = getDaoFactory().getSectorDao().findSector(id);
            return sectorTx;
        });

        return sector;
    }

    @Override
    public void updateSector(Sector sector) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getSectorDao().updateSector(sector);
            }
        });
    }

    @Override
    public void deleteSector(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getSectorDao().deleteSector(id);
            }
        });
    }
}
