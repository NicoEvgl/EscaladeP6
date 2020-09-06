package org.nico.business.contract.manager;

import org.nico.model.beans.ClimbingSite;

import java.util.List;

public interface ClimbingSiteManager {
    void createClimbingSite(ClimbingSite climbingSite);
    List<ClimbingSite> findClimbingSiteList();
    List<ClimbingSite> findClimbingSiteSearchRequest(String name, String region, Integer nbRoutes, String cotation);
}
