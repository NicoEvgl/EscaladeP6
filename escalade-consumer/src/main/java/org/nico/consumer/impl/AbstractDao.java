package org.nico.consumer.impl;

import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

public abstract class AbstractDao {

    @Inject
    @Named("dataSourceEscalade")
    private static DataSource dataSourceEscalade;

    protected DataSource getDataSourceEscalade() {
        return dataSourceEscalade;
    }

    public static void setDataSourceEscalade(DataSource dataSourceEscalade) {
        AbstractDao.dataSourceEscalade = dataSourceEscalade;
    }
}

