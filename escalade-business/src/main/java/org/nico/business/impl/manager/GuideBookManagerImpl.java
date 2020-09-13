package org.nico.business.impl.manager;

import org.nico.business.contract.manager.GuideBookManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.beans.ClimbingSite;
import org.nico.model.beans.GuideBook;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

public class GuideBookManagerImpl extends AbstractManager implements GuideBookManager {

    @Override
    public void createGuideBook(GuideBook guideBook) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getGuideBookDao().createGuideBook(guideBook);
            }
        });
    }

    @Override
    public List<GuideBook> findGuideBookList() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<GuideBook> guideBookList = transactionTemplate.execute(transactionStatus -> {
            List<GuideBook> guideBookListTx = new ArrayList<>();
            guideBookListTx = getDaoFactory().getGuideBookDao().findGuideBookList();
            return  guideBookListTx;
        });

        return guideBookList;
    }

    @Override
    public List<GuideBook> findGuideBookSearchRequest(String name, String region) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        List<GuideBook> guideBookList = transactionTemplate.execute(transactionStatus -> {
            List<GuideBook> guideBookListTx = new ArrayList<>();
            guideBookListTx = getDaoFactory().getGuideBookDao().findGuideBookSearchRequest(name, region);
            return  guideBookListTx;
        });

        return guideBookList;
    }


}
