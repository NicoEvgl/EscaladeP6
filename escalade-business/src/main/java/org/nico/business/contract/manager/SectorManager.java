package org.nico.business.contract.manager;

import org.nico.model.beans.Sector;

import java.util.List;

public interface SectorManager {
    void createSector(Sector sector);
    List<Sector> findSectorByClimbingSiteId(Integer id);
}
