package org.nico.business.impl.manager;

import org.nico.business.contract.manager.MemberManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.beans.Member;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Member> findMemberList() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        List<Member> memberList = transactionTemplate.execute(transactionStatus -> {
            List<Member> memberListTx = new ArrayList<>();
            memberListTx = getDaoFactory().getMemberDao().findMemberList();
            return  memberListTx;
        });
        return memberList;
    }

    @Override
    public void updateMember(Member member) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getMemberDao().updateMember(member);
            }
        });

    }

    @Override
    public void deleteMember(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getMemberDao().deleteMember(id);
            }
        });
    }

    @Override
    public Member findMemberByUsername (String username, Object value) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        Member member = transactionTemplate.execute(transactionStatus -> {
            Member memberTx = new Member();
            memberTx = getDaoFactory().getMemberDao().findMemberByUsername(username, value);
            return  memberTx;
        });
        return member;
    }
}
