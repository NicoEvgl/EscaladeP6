package org.nico.consumer.contract.dao;

import org.nico.model.beans.GuideBook;

import java.util.List;

public interface GuideBookDao {
    void createGuideBook(GuideBook guideBook);
    List<GuideBook> findGuideBookList();
    List<GuideBook> findGuideBookSearchRequest(String name, String region);
}
