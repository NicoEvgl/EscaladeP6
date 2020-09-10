package org.nico.consumer.contract.dao;

import org.nico.model.beans.Photo;

import java.util.List;

public interface PhotoDao {
    void createPhoto(Photo photo);
    List<Photo> findPhotoList();
    Photo findPhoto(Integer id);
    List<Photo> findPhotoByClimbingSiteId(Integer id);
    void deletePhoto(Integer id);
}
