package org.nico.consumer.contract.dao;


import org.nico.model.beans.Route;

import java.util.List;

public interface RouteDao {
    void createRoute(Route route);
    List<Route> findRouteBySectorId(Integer id);
}
