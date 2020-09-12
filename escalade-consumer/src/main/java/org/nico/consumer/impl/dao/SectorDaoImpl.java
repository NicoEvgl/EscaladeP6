package org.nico.consumer.impl.dao;

import org.nico.consumer.contract.dao.SectorDao;
import org.nico.consumer.impl.AbstractDao;
import org.nico.consumer.impl.rowmapper.SectorRowMapper;
import org.nico.model.beans.Sector;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;
import java.util.List;

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

    @Override
    public List<Sector> findSectorByClimbingSiteId(Integer id) {
        String sql = "SELECT * FROM public.sector WHERE climbingsite_id = " + id + " ORDER BY id";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSourceEscalade());
        SectorRowMapper sectorRowMapper = new SectorRowMapper();
        List<Sector> sectorList = jdbcTemplate.query(sql, sectorRowMapper);

        return sectorList;
    }

    @Override
    public Sector findSector(Integer id) {
        String sql = "SELECT * FROM public.sector WHERE id = :id";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", id, Types.INTEGER);

        SectorRowMapper sectorRowMapper = new SectorRowMapper();
        Sector sector = namedParameterJdbcTemplate.queryForObject(sql, mapSqlParameterSource, sectorRowMapper);

        return sector;
    }

    @Override
    public void updateSector(Sector sector) {
        String sql = "UPDATE public.sector SET "
                + "name = :name, "
                + "description = :description, "
                + "climbingsite_id = :climbingSiteId "
                + "WHERE id = :id";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSourceEscalade());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("id", sector.getId(), Types.INTEGER);
        mapSqlParameterSource.addValue("name", sector.getName(), Types.VARCHAR);
        mapSqlParameterSource.addValue("description", sector.getDescription(), Types.VARCHAR);
        mapSqlParameterSource.addValue("climbingSiteId", sector.getClimbingSite().getId(), Types.INTEGER);


        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);

    }
}
