package lab.neobradley.simple.application.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lab.neobradley.simple.application.config.ds.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@Configuration
//@MapperScan(basePackages = "")
@Slf4j
public class MybatisConfig {

    @Value("${spring.datasource.master.driver-class-name}")
    private String masterDriverClassName;
    @Value("${spring.datasource.master.jdbcUrl}")
    private String masterJdbcUrl;
    @Value("${spring.datasource.master.username}")
    private String masterUsername;
    @Value("${spring.datasource.master.password}")
    private String masterPassword;

    @Bean("master")
    @Primary
    public DataSource master() {
        return getDataSource(masterDriverClassName, masterJdbcUrl, masterUsername, masterPassword);
    }

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("master", master());
        // 将 rpt 数据源作为默认指定的数据源
        dynamicDataSource.setDefaultDataSource(master());
        // 将其他数据源作为指定的数据源
        dynamicDataSource.setDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dynamicDataSource());

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);

        Interceptor[] plugins = new Interceptor[1];
        plugins[0] = paginationInterceptor();
        sqlSessionFactory.setPlugins(plugins);
//        log.warn("Connecting : {}, User : {}, Pass : {}", masterJdbcUrl, masterUsername, masterPassword);
        return sqlSessionFactory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //分页限制1000
        paginationInterceptor.setLimit(1000);
        return paginationInterceptor;
    }


    private DataSource getDataSource(String driverClassName, String jdbcUrl, String username, String password) {
        DataSourceBuilder<?> dsBuilder = DataSourceBuilder.create();

        try {
            dsBuilder.driverClassName(driverClassName);
            dsBuilder.url(jdbcUrl);
            dsBuilder.username(username);
            dsBuilder.password(password);
        } catch (Exception e) {
            log.error("getDataSource error", e);
        }

        return dsBuilder.build();
    }
}
