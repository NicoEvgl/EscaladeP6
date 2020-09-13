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

    @Override
    public GuideBook findGuideBook(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        GuideBook guideBook = transactionTemplate.execute(transactionStatus -> {
            GuideBook guideBookTx;
            guideBookTx = getDaoFactory().getGuideBookDao().findGuideBook(id);
            return guideBookTx;
        });

        return guideBook;
    }

    @Override
    public GuideBook findGuideBookByAttribute(String attribute, String attributeValue) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        GuideBook guideBook = transactionTemplate.execute(transactionStatus -> {
            GuideBook guideBookTx = new GuideBook();
            guideBookTx = getDaoFactory().getGuideBookDao().findGuideBookByAttribute(attribute, attributeValue);
            return guideBookTx;
        });

        return guideBook;
    }
}
