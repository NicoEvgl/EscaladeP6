package org.nico.business.impl.manager;

import org.nico.business.contract.manager.GuideBookReservationManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.beans.GuideBookReservation;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<GuideBookReservation> findGuideBookReservationRequestList(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<GuideBookReservation> guideBookReservationList = transactionTemplate.execute(transactionStatus -> {
            List<GuideBookReservation> guideBookReservationListTx = new ArrayList<>();
            guideBookReservationListTx = getDaoFactory().getGuideBookReservationDao().findGuideBookReservationRequestList(id);
            return  guideBookReservationListTx;
        });

        return guideBookReservationList;
    }

    @Override
    public List<GuideBookReservation> findGuideBookReservationListByUserId(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<GuideBookReservation> guideBookReservationList = transactionTemplate.execute(transactionStatus -> {
            List<GuideBookReservation> guideBookReservationListTx = new ArrayList<>();
            guideBookReservationListTx = getDaoFactory().getGuideBookReservationDao().findGuideBookReservationListByUserId(id);
            return  guideBookReservationListTx;
        });

        return guideBookReservationList;
    }
}
