package org.nico.consumer.contract.dao;

import org.nico.model.beans.ClimbingSite;

import java.util.List;

public interface ClimbingSiteDao {
    void createClimbingSite(ClimbingSite climbingSite);
    List<ClimbingSite> findClimbingSiteList();
    ClimbingSite findClimbingSite(Integer id);
    List<ClimbingSite> findClimbingSiteByUserId(Integer id);
    ClimbingSite findClimbingSiteByAttribute(String attribute, Object attributeValue);
    List<ClimbingSite> findClimbingSiteSearchRequest(String name, String region, Integer nbRoutes, String quotation);
    void updateClimbingSite(ClimbingSite climbingSite);

}
