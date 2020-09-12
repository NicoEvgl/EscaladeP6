package org.nico.consumer.contract.dao;


import org.nico.model.beans.Route;

import java.util.List;

public interface RouteDao {
    void createRoute(Route route);
    List<Route> findRouteBySector(Integer id);
    Route findRoute(Integer id);
    void updateRoute(Route route);
    void deleteRoute(Integer id);
}
