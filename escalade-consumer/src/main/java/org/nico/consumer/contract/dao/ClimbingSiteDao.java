package org.nico.consumer.contract.dao;

import org.nico.model.beans.ClimbingSite;

import java.util.List;

public interface ClimbingSiteDao {
    void createClimbingSite(ClimbingSite climbingSite);
    List<ClimbingSite> findClimbingSiteList();
    List<ClimbingSite> findClimbingSiteSearchRequest(String name, String region, Integer nbRoutes, String quotation);
    ClimbingSite findClimbingSite(Integer id);
    void editClimbingSite(ClimbingSite climbingSite);
    List<ClimbingSite> findClimbingSiteByUserId(Integer id);
}
