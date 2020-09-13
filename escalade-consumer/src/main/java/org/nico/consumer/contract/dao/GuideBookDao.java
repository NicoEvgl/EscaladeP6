package org.nico.consumer.contract.dao;

import org.nico.model.beans.GuideBook;

import java.util.List;

public interface GuideBookDao {
    void createGuidebook(GuideBook guideBook);
    List<GuideBook> findGuideBookList();
}
