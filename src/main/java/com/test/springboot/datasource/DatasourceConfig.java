package com.test.springboot.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration //相当于spring中xml中的<Beans>
//@MapperScan(basePackages = "com.test.springboot.mapper", sqlSessionTemplateRef = "sqlSessionTemplate") //要么在每个接口上打上@Mapper,要么在配置类或启动类上使用@MapperScan
@MapperScan(basePackages = "com.test.springboot.mapper") //要么在每个接口上打上@Mapper,要么在配置类或启动类上使用@MapperScan
public class DatasourceConfig {

    @Value("${mybatis.mapper-locations}")
    private String mapper_location;


    @Value("${spring.datasource.druid.first.url}")
    private String url;

    @Value("${spring.datasource.druid.first.username}")
    private String user;

    @Value("${spring.datasource.druid.first.password}")
    private String password;

    @Value("${spring.datasource.druid.first.driver-class-name}")
    private String driverClass;


    @Value("${spring.datasource.druid.second.url}")
    private String url2;

    @Value("${spring.datasource.druid.second.username}")
    private String user2;

    @Value("${spring.datasource.druid.second.password}")
    private String password2;

    @Value("${spring.datasource.druid.second.driver-class-name}")
    private String driverClass2;


    @Primary
    @Bean(name = "first")
    @ConfigurationProperties("spring.datasource.druid.first")
    public DataSource setDataSource() {
        // return DruidDataSourceBuilder.create().build();

        // 使用druid 则这样注入 dataSource，不需要则直接 DataSourceBuilder.create().build()
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        try {
            // 如果想使用 Druid 的sql监控则，此处需要写 stat
            dataSource.setFilters("stat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }


    @Bean(name = "second")
    @ConfigurationProperties("spring.datasource.druid.second")
    public DataSource setDataSource2() {
        // return DruidDataSourceBuilder.create().build();

        // 使用druid 则这样注入 dataSource，不需要则直接 DataSourceBuilder.create().build()
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass2);
        dataSource.setUrl(url2);
        dataSource.setUsername(user2);
        dataSource.setPassword(password2);
        try {
            // 如果想使用 Druid 的sql监控则，此处需要写 stat
            dataSource.setFilters("stat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;

    }


    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("first", setDataSource());
        dataSourceMap.put("second", setDataSource2());
        // 将 first 数据源作为默认指定的数据源
        dynamicDataSource.setDefaultDataSource(setDataSource());
        // 将 first 和 second 数据源作为指定的数据源
        dynamicDataSource.setDataSources(dataSourceMap);
        return dynamicDataSource;
    }


    @Primary
    @Bean(name = "firstSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
        bean.setDataSource(dynamicDataSource());
        // 开启数据源的小驼峰映射
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);

        //如果重写了 SqlSessionFactory 需要在初始化的时候手动将 mapper 地址 set到 factory 中，否则会报错：
        //org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapper_location));
        return bean.getObject();
    }

//    @Bean(name = "firstSqlSessionFactory")
//    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
//        sessionFactory.setDataSource(dynamicDataSource());
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapper_location));    // 扫描映射文件
//        return sessionFactory;
//    }


//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
//        return new DataSourceTransactionManager(dynamicDataSource());
//    }

    @Primary
    @Bean(name = "firstTransactionManager")
    public DataSourceTransactionManager setTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    @Primary
    @Bean(name = "firstSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
