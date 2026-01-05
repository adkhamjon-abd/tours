package org.example.repository;

import org.example.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CompanyRepository {

    private final SessionFactory sessionFactory;

    public CompanyRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Company save(Company company) {

        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(company);
            tx.commit();
            return company;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Optional<Company> findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Company.class, id));
        }
    }

    public List<Company> findAll() {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM Company", Company.class).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void deleteById(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.createQuery(
                            "DELETE FROM Rating r WHERE r.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(Company existing) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Company company = session.find(Company.class, existing.getId());
            if (company != null) {
                company.setName(existing.getName());
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
