package ru.horologer.db.relation.repo.transaction;

import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static ru.horologer.core.AppConfiguration.getLogger;

/**
 * Class for control connection pool
 */
public class DBConnectionPool {

    private final DataSource datasource;

    DBConnectionPool(ApachePoolPropertiesDecorator propertiesDecorator) {
        this.datasource = new DataSource();
        datasource.setPoolProperties(propertiesDecorator.getP());
    }

    /**
     * Get connection from pool
     *
     * @return stable connection
     */
    public Connection getConnection() {
        try {
            Connection connection = datasource.getConnection();
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            getLogger().log(e);
            return null;
        }
    }

    /**
     * Free connection for other using
     *
     * @param conn used connection
     */
    public void freeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            getLogger().log(e);
        }
    }

    /**
     * Close pool with all connections
     */
    public void close() {
        getLogger().log("Close all connections in pool!");
        datasource.close(true);
    }

}
