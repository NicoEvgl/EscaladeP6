package org.nico.business.contract.manager;

import org.nico.model.beans.Sector;

import java.util.List;

public interface SectorManager {
    void createSector(Sector sector);
    List<Sector> findSectorList();
    List<Sector> findSectorByClimbingSite(Integer id);
    Sector findSector(Integer id);
    void updateSector(Sector sector);
    void deleteSector(Integer id);
}
