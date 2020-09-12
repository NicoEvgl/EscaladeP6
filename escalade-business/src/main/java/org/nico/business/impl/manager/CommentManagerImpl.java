package org.nico.business.impl.manager;

import org.nico.business.contract.manager.CommentManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.beans.Comment;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class CommentManagerImpl extends AbstractManager implements CommentManager {

    @Override
    public void createComment(Comment comment) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getCommentDao().createComment(comment);
            }
        });
    }
}
