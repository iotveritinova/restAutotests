package tripDemo.hibrnate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Hibernate {

    public void HiberConnection() {
        Configuration configuration = new Configuration();
        //configuration.configure(); - нужен для использования файла .cfg.xml, не нужен для .properties
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        sessionFactory.openSession();
    }

}
