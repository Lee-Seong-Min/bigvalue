package kr.co.bigvalue.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "bigvalueEntityManagerFactory",
        transactionManagerRef = "bigvalueTransactionManager",
        basePackages = {"kr.co.bigvalue.repository"}
)
@MapperScan(
        basePackages = {"kr.co.bigvalue.mapper"},
        sqlSessionTemplateRef = "bigvalueSqlSessionTemplate"
)
public class BigvalueDbConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Primary
    @Bean(name="bigvalueDatasource")
    @ConfigurationProperties(prefix = "bigvalue.datasource")
    public DataSource _dataSource() {
        final DataSource dataSource =
                DataSourceBuilder.create()
                        .build();

        return dataSource;
    }

    @Primary
    @Bean(name = "bigvalueEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean _entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("bigvalueDatasource") DataSource dataSource) {

        final Map<String, Object> properties =
                new LinkedHashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "update");

        final LocalContainerEntityManagerFactoryBean factoryBean =
                builder.dataSource(dataSource)
                        .packages("kr.co.bigvalue.domain")
                        .persistenceUnit("bigvalue")
                        .properties(properties)
                        .build();

        return factoryBean;
    }

    @Primary
    @Bean(name = "bigvalueTransactionManager")
    public PlatformTransactionManager _transactionManager(
            @Qualifier("bigvalueEntityManagerFactory") EntityManagerFactory entityManagerFactory) throws Exception {

        // JPA transactionManager
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);

        return jpaTransactionManager;
    }

    @Primary
    @Bean(name = "bigvalueSqlSessionFactory")
    public SqlSessionFactory _sqlSessionFactory(
            @Qualifier("bigvalueDatasource")DataSource dataSource) throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mappers/*Mapper.xml"));
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
        factoryBean.setTypeAliasesPackage("kr.co.bigvalue.domain");

        return factoryBean.getObject();
    }

    @Primary
    @Bean(name = "bigvalueSqlSessionTemplate")
    public SqlSessionTemplate _sqlSessionTemplate(@Qualifier("bigvalueSqlSessionFactory")SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
