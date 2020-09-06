package org.nico.consumer.contract.dao;

import org.nico.model.beans.ClimbingSite;

import java.util.List;

public interface ClimbingSiteDao {
    void createClimbingSite(ClimbingSite climbingSite);
    List<ClimbingSite> findClimbingSiteList();
    List<ClimbingSite> findClimbingSiteSearchRequest(String name, String region, String nbRoutes, String cotation);
}
