package org.nico.consumer.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

public abstract class AbstractDao {

    @Inject
    @Named("dataSourceEscalade")

    private static DataSource dataSourceEscalade;

    protected DataSource getDataSourceEscalade() { return dataSourceEscalade; }

    public static void setDataSourceEscalade(DataSource dataSourceEscalade) {
        AbstractDao.dataSourceEscalade = dataSourceEscalade;
    }
}
