package pers.ycm.sbdefault.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanchengman
 * @date 2021-03-09
 */
@Component
public class CustomRoutingDataSource  extends AbstractRoutingDataSource {

    @Resource(name = "writeDataSourceProperties")
    private DataSourceProperties writeProperties;

    @Resource(name = "readDataSourceProperties")
    private DataSourceProperties readProperties;

    @Override
    public void afterPropertiesSet() {
        DataSource writeDataSource =
                writeProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        DataSource readDataSource =
                readProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();

        setDefaultTargetDataSource(writeDataSource);

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceHolder.WRITE_DATASOURCE, writeDataSource);
        dataSourceMap.put(DataSourceHolder.READ_DATASOURCE, readDataSource);
        setTargetDataSources(dataSourceMap);

        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String key = DataSourceHolder.getDataSource();

        if (key == null) {
            // default datasource
            return DataSourceHolder.WRITE_DATASOURCE;
        }

        return key;
    }
}
