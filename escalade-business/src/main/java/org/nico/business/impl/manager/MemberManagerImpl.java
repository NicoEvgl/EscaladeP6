package org.nico.business.impl.manager;

import org.nico.business.contract.manager.MemberManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.beans.Member;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class MemberManagerImpl extends AbstractManager implements MemberManager {

    @Override
    public void createMember(Member member) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getMemberDao().createMember(member);
            }
        });

    }
}
