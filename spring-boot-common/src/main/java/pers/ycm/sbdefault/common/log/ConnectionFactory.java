package pers.ycm.sbdefault.common.log;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    private static interface Singleton {
        final ConnectionFactory INSTANCE = new ConnectionFactory();
    }

    private HikariDataSource dataSource;

    private ConnectionFactory() {
        Configuration cfg = null;
        try {
            cfg = new PropertiesConfiguration("application.properties");
            String user = cfg.getString("db.username");
            String password = cfg.getString("db.password");
            String url = cfg.getString("db.url");
            String driverClassName = "com.mysql.cj.jdbc.Driver";

            HikariConfig config = new HikariConfig();
            config.setDriverClassName(driverClassName);
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(password);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepstmtCacheSize", "250");
            config.addDataSourceProperty("prepstmtCacheSqlLimit", "2048");
            config.setConnectionTimeout(8 * 60 * 60);
            this.dataSource = new HikariDataSource(config);
        } catch (ConfigurationException e) {
            logger.error("initDataSourceErr", e);
        }
    }

    public static Connection getDatabaseConnection() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }
}
