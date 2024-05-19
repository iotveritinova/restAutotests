package tripDemo.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import tripDemo.dictionaries.ServiceEnum;
import tripDemo.model.CompanyEntity;
import tripDemo.model.PassengerEntity;
import tripDemo.model.TripEntity;
import tripDemo.service.ConfigQA;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BaseConnection {
    private final Map<ServiceEnum, Session> connectionMap;
    private final ConfigQA configQA;

    private static BaseConnection instance;

    private BaseConnection() {
        connectionMap = new ConcurrentHashMap<>();
        configQA = ConfigQA.getInstance();
    }

    public static BaseConnection getInstance() {
        if (instance == null) {
            instance = new BaseConnection();
        }
        return instance;
    }

    public Session getSession(ServiceEnum serviceEnum) {
        if (Objects.nonNull(serviceEnum)) {
            return connectionMap.computeIfAbsent(serviceEnum,
                    a -> {
                        Configuration configuration = new Configuration();
                        configuration.setProperties(getSettings(a));
                        getClasses(a).forEach(configuration::addAnnotatedClass);
                        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties()).build();
                        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                        return sessionFactory.openSession();
                    });
        }
        throw new IllegalArgumentException();
    }

    public void closeConnection(ServiceEnum serviceEnum) {
        connectionMap.computeIfPresent(serviceEnum,
                (a, b) -> {
                    connectionMap.remove(a).close();
                    return null;
                });
    }

    private Properties getSettings(ServiceEnum serviceEnum) {
        Properties properties = new Properties();
        ConnectionProperties connectionProperties = configQA.getDbConnectionDataMap().get(serviceEnum);
        properties.put(Environment.DRIVER, connectionProperties.getDriver());
        properties.put(Environment.DIALECT, connectionProperties.getDialect());
        properties.put(Environment.URL, connectionProperties.getUrl());
        properties.put(Environment.USER, connectionProperties.getUser());
        properties.put(Environment.PASS, connectionProperties.getPassword());
        return properties;
    }

    private List<Class<?>> getClasses(ServiceEnum serviceEnum) {
        List<Class<?>> classes = new ArrayList<>();
        switch (serviceEnum) {
            case TRIP:
                classes.add(CompanyEntity.class);
                classes.add(TripEntity.class);
                classes.add(PassengerEntity.class);
        }
        return classes;
    }
}
