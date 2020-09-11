package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.SectorDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.model.beans.Sector;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;

public class SectorDaoImpl extends AbstractDao implements SectorDao {

    @Override
    public void createSector(Sector sector) {
        String sql = "INSERT INTO public.sector (name, description, climbingsite_id)"
                + "VALUES (:name, :description, :climbingsiteId)";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", sector.getName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("description", sector.getDescription(), Types.VARCHAR);
        mapSqlParameterSource.addValue("climbingsiteId", sector.getClimbingSite().getId(), Types.INTEGER);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);

    }
}
