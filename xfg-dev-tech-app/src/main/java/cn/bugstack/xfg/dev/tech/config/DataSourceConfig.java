package cn.bugstack.xfg.dev.tech.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ignite.configuration.ConnectorConfiguration;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Configuration
    @MapperScan(basePackages = "cn.bugstack.xfg.dev.tech.infrastructure.ignite.dao", sqlSessionFactoryRef = "igniteSqlSessionFactory")
    static class IgniteMyBatisConfig {

        @Bean("igniteDataSource")
        @ConfigurationProperties(prefix = "spring.ignite.datasource")
        public DataSource igniteDataSource(Environment environment) {
            IgniteConfiguration igniteConfig = new IgniteConfiguration();
            DataStorageConfiguration dataStorageConfig = new DataStorageConfiguration();
            DataRegionConfiguration defaultDataRegionConfig = new DataRegionConfiguration();
            defaultDataRegionConfig.setPersistenceEnabled(false);
            dataStorageConfig.setDefaultDataRegionConfiguration(defaultDataRegionConfig);
            igniteConfig.setDataStorageConfiguration(dataStorageConfig);

            ConnectorConfiguration configuration = new ConnectorConfiguration();
            configuration.setIdleTimeout(6000);
            configuration.setThreadPoolSize(100);
            configuration.setIdleTimeout(60000);

            igniteConfig.setConnectorConfiguration(configuration);

            return DataSourceBuilder.create()
                    .url(environment.getProperty("spring.ignite.datasource.url"))
                    .driverClassName(environment.getProperty("spring.ignite.datasource.driver-class-name"))
                    .build();
        }

        @Bean("igniteSqlSessionFactory")
        public SqlSessionFactory igniteSqlSessionFactory(DataSource igniteDataSource) throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(igniteDataSource);
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mapper/ignite/*.xml"));
            return factoryBean.getObject();
        }
    }

    @Configuration
    @MapperScan(basePackages = "cn.bugstack.xfg.dev.tech.infrastructure.mysql.dao", sqlSessionFactoryRef = "mysqlSqlSessionFactory")
    static class MysqlMyBatisConfig {

        @Bean("mysqlDataSource")
        @ConfigurationProperties(prefix = "spring.mysql.datasource")
        public DataSource mysqlDataSource(Environment environment) {
            return DataSourceBuilder.create()
                    .url(environment.getProperty("spring.mysql.datasource.url"))
                    .driverClassName(environment.getProperty("spring.mysql.datasource.driver-class-name"))
                    .build();
        }

        @Bean("mysqlSqlSessionFactory")
        public SqlSessionFactory mysqlSqlSessionFactory(DataSource mysqlDataSource) throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(mysqlDataSource);
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mapper/mysql/*.xml"));
            return factoryBean.getObject();
        }
    }

}
