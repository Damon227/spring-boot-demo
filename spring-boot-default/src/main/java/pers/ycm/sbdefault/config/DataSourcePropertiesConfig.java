package pers.ycm.sbdefault.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author yuanchengman
 * @date 2021-03-09
 */
@Configuration
public class DataSourcePropertiesConfig {
    @Primary
    @Bean("writeDataSourceProperties")
    @ConfigurationProperties("write.db")
    public DataSourceProperties writeDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean("readDataSourceProperties")
    @ConfigurationProperties("read.db")
    public DataSourceProperties readDataSourceProperties(){
        return new DataSourceProperties();
    }
}
