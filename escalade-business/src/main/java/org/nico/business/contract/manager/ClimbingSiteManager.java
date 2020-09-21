package org.nico.business.contract.manager;

import org.nico.model.beans.ClimbingSite;

import java.util.List;

public interface ClimbingSiteManager {
    void createClimbingSite(ClimbingSite climbingSite);
    List<ClimbingSite> findClimbingSiteList();
    ClimbingSite findClimbingSite(Integer id);
    List<ClimbingSite> findClimbingSiteByUserId(Integer id);
    ClimbingSite findClimbingSiteByAttribute(String attribute, Object attributeValue);
    List<ClimbingSite> findClimbingSiteSearchRequest(String name, String region, Integer nbRoutes, String quotation);
    void updateClimbingSite(ClimbingSite climbingSite);
    void deleteClimbingSite(Integer id);
    void addTag(Integer id);
    void deleteTag(Integer id);
}
