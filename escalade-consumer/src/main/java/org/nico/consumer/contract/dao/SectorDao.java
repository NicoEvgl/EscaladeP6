package org.nico.consumer.contract.dao;

import org.nico.model.beans.Sector;

import java.util.List;

public interface SectorDao {
    void createSector(Sector sector);
    List<Sector> findSectorByClimbingSiteId(Integer id);
}
