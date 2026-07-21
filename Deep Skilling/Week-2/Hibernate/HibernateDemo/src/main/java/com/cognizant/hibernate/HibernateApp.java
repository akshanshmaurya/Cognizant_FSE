package com.cognizant.hibernate;

import com.cognizant.hibernate.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateApp {
    public static void main(String[] args) {
        System.out.println("Initializing Hibernate SessionFactory...");
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {

            session.beginTransaction();
            session.persist(new Country("US", "United States"));
            session.persist(new Country("IN", "India"));
            session.getTransaction().commit();

            System.out.println("Countries Persisted via Hibernate ORM:");
            session.createQuery("from Country", Country.class)
                   .getResultList()
                   .forEach(System.out::println);
        }
    }
}
