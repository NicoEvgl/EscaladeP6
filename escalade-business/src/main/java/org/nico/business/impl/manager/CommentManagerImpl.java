package org.nico.business.impl.manager;

import org.nico.business.contract.manager.CommentManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.beans.Comment;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Comment> findCommentByClimbingSite(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Comment> commentListByClimbingSite = transactionTemplate.execute(transactionStatus -> {
            List<Comment> commentListTx = new ArrayList<>();
            commentListTx = getDaoFactory().getCommentDao().findCommentByClimbingSite(id);
            return  commentListTx;
        });

        return commentListByClimbingSite;
    }

    @Override
    public Comment findComment(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        Comment comment = transactionTemplate.execute(transactionStatus -> {
            Comment commentTx;
            commentTx = getDaoFactory().getCommentDao().findComment(id);
            return commentTx;
        });

        return comment;    }

    @Override
    public void updateComment(Comment comment) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getCommentDao().updateComment(comment);
            }
        });
    }

    @Override
    public void deleteComment(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getCommentDao().deleteComment(id);
            }
        });
    }
}
