package pl.sda.arppl4.student.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class HibernateUtil {
    public static final SessionFactory sessionFactory;

    static {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}