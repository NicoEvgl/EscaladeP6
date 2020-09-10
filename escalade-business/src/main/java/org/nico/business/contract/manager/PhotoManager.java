package org.nico.business.contract.manager;

import org.nico.model.beans.Photo;

import java.util.List;

public interface PhotoManager {
    void createPhoto(Photo photo);
    List<Photo> findPhotoList();
    Photo findPhoto(Integer id);
    List<Photo> findPhotoByClimbingSiteId(Integer id);
    void updatePhoto(Photo photo);
    void deletePhoto(Integer id);

}
