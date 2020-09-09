package org.nico.business.contract.manager;

import org.nico.model.beans.ClimbingSite;

import java.util.List;

public interface ClimbingSiteManager {
    void createClimbingSite(ClimbingSite climbingSite);
    List<ClimbingSite> findClimbingSiteList();
    ClimbingSite findClimbingSite(Integer id);
    List<ClimbingSite> findClimbingSiteSearchRequest(String name, String region, Integer nbRoutes, String quotation);
    void updateClimbingSite(ClimbingSite climbingSite);
    List<ClimbingSite> findClimbingSiteByUserId(Integer id);
}
