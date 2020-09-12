package org.nico.business.contract.manager;

import org.nico.model.beans.Route;

import java.util.List;

public interface RouteManager {
    void createRoute(Route route);
    List<Route> findRouteBySector (Integer id);
    Route findRoute(Integer id);
    void updateRoute(Route route);
    void deleteRoute(Integer id);
}
