package ru.job4j.cars.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.repository.CrudRepository;

public class TestHbmConfig {

    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder()
            .configure().build();
    private static final SessionFactory SF = new MetadataSources(REGISTRY).buildMetadata()
            .buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(SF);

    public CrudRepository getCrudRepository() {
        return crudRepository;
    }

    public void wipeTable(String query) {
        Session session = SF.openSession();
        try {
            session.beginTransaction();
            session.createQuery(query)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
