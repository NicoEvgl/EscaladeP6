package org.nico.consumer.contract.dao;

import org.nico.model.beans.Photo;

import java.util.List;

public interface PhotoDao {
    void createPhoto(Photo photo);
    List<Photo> findPhotoList();

    List<Photo> findPhotoByClimbingSiteId(Integer id);
}
