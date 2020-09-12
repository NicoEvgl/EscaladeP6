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
    public List<Sector> findSectorByClimbingSiteId(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Sector> sectorList = transactionTemplate.execute(transactionStatus -> {
            List<Sector> sectorListTx = new ArrayList<>();
            sectorListTx = getDaoFactory().getSectorDao().findSectorByClimbingSiteId(id);
            return  sectorListTx;
        });

        return sectorList;
    }
}
