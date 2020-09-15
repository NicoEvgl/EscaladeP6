package org.nico.business.impl.manager;

import org.nico.business.contract.manager.GuideBookReservationManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.beans.GuideBookReservation;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class GuideBookReservationManagerImpl extends AbstractManager implements GuideBookReservationManager {

    @Override
    public void createGuideBookReservation(GuideBookReservation guideBookReservation) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getGuideBookReservationDao().createGuideBookReservation(guideBookReservation);
            }
        });
    }

    @Override
    public GuideBookReservation findGuideBookReservationByGuideBookAndUserId(Integer guideBookId, Integer userId) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        GuideBookReservation guideBookReservation = transactionTemplate.execute(transactionStatus -> {
            GuideBookReservation guideBookReservationTx;
            guideBookReservationTx = getDaoFactory().getGuideBookReservationDao().findGuideBookReservationByGuideBookAndUserId(guideBookId, userId);
            return guideBookReservationTx;
        });

        return guideBookReservation;
    }
}
