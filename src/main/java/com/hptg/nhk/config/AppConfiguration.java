package com.hptg.nhk.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.Properties;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.hptg.nhk")
public class AppConfiguration implements WebMvcConfigurer {

    @Bean
    public DataSource myDataSource(){

        /* create connection pool */
        ComboPooledDataSource myDataSource = new ComboPooledDataSource();
        /* set the jdbc driver*/
        try{
            myDataSource.setDriverClass("org.postgresql.Driver");
        }catch (PropertyVetoException exc){
            throw new RuntimeException(exc);
        }
        // set database connection props
        myDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/NHK_News?useSSL=false");
        myDataSource.setUser("postgres");
        myDataSource.setPassword("Hptg19940215");

        // ste connection pool props
        myDataSource.setInitialPoolSize(5);
        myDataSource.setMinPoolSize(5);
        myDataSource.setMaxPoolSize(20);
        myDataSource.setMaxIdleTime(3000);

        return myDataSource;
    }

    private Properties getHibernateProperties() {

        // set hibernate properties
        Properties props = new Properties();
        props.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQL82Dialect" );
        props.setProperty("hibernate.show_sql", "true");
        return props;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(){
        /*create session factory*/
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        /*set the properties*/
        sessionFactory.setDataSource(myDataSource());
        sessionFactory.setPackagesToScan("com.hptg.nhk.Model");
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

}
