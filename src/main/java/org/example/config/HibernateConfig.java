package org.example.config;

import org.example.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")  // Load application.properties file
public class HibernateConfig {

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show-sql}")
    private boolean showSql;

    @Value("${hibernate.hbm2ddl-auto}")
    private String hbm2ddlAuto;

    @Value("${hibernate.current-session-context-class}")
    private String currentSessionContextClass;

    // DataSource properties for connecting to DB
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public SessionFactory sessionFactory() {
        try {
            Configuration configuration = new Configuration();

            // Set Hibernate properties directly from application.properties using @Value
            configuration.setProperty("hibernate.dialect", dialect);
            configuration.setProperty("hibernate.show_sql", String.valueOf(showSql));
            configuration.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
            configuration.setProperty("hibernate.current_session_context_class", currentSessionContextClass);

            // Set the datasource properties for connecting to the database
            configuration.setProperty("hibernate.connection.url", url);
            configuration.setProperty("hibernate.connection.username", username);
            configuration.setProperty("hibernate.connection.password", password);
            configuration.setProperty("hibernate.connection.driver_class", driverClassName);

            // Add your entity classes (you will still need to list them here)
            configuration.addAnnotatedClass(Booking.class);
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Rating.class);
            configuration.addAnnotatedClass(Tour.class);
            configuration.addAnnotatedClass(User.class);

            // Hibernate ServiceRegistry for SessionFactory
            ServiceRegistry serviceRegistry = new org.hibernate.boot.registry.StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create SessionFactory", e);
        }
    }
}
