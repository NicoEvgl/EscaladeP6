package org.nico.business.contract.manager;

import org.nico.model.beans.GuideBook;

import java.util.List;

public interface GuideBookManager {
    void createGuideBook(GuideBook guideBook);
    List<GuideBook> findGuideBookList();
    List<GuideBook> findGuideBookSearchRequest(String name, String region);
    GuideBook findGuideBook(Integer id);
}
