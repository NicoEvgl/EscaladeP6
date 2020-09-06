package org.nico.business.impl.manager;

import org.nico.business.contract.manager.PhotoManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.beans.Photo;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

public class PhotoManagerImpl extends AbstractManager implements PhotoManager {

    @Override
    public void createPhoto(Photo photo){
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                getDaoFactory().getPhotoDao().createPhoto(photo);
            }
        });
    }

    @Override
    public List<Photo> findPhotoList() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        List<Photo> photoList = transactionTemplate.execute(transactionStatus -> {
            List<Photo> photoListTx = new ArrayList<>();
            photoListTx = getDaoFactory().getPhotoDao().findPhotoList();
            return photoListTx;
        });
        return photoList;
    }

    @Override
    public Photo findPhoto(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        Photo photo = transactionTemplate.execute(transactionStatus -> {
            Photo photoTx;
            photoTx = getDaoFactory().getPhotoDao().findPhoto(id);
            return photoTx;
        });

        return photo;
    }

    @Override
    public List<Photo> findPhotoByClimbingSiteId(Integer id) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Photo> photoList = transactionTemplate.execute(transactionStatus -> {
            List<Photo> photoListTx = new ArrayList<>();
            photoListTx = getDaoFactory().getPhotoDao().findPhotoByClimbingSiteId(id);
            return  photoListTx;
        });

        return photoList;
    }
}
