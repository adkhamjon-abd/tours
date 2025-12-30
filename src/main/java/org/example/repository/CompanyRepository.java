package org.example.repository;

import org.example.config.HibernateUtil;
import org.example.model.Company;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CompanyRepository {

    private final Map<Integer, Company> companies = new HashMap<>();
    private int nextId = 6;
    public CompanyRepository(){
        companies.put(1, new Company(1, "Green Travel"));
        companies.put(2, new Company(2, "World Tours"));
        companies.put(3, new Company(3, "Samo Travel"));
        companies.put(4, new Company(4, "Holiday Abroad"));
        companies.put(5, new Company(5, "Afsona Travel"));
    }
    public Company save(Company company){

        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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

    public Optional<Company> findById(int id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.find(Company.class, id));
        }
    }

    public List<Company> findAll() {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.createQuery(
                            "DELETE FROM Rating r WHERE r.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit();

        }catch (Exception e) {
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
            session = HibernateUtil.getSessionFactory().openSession();
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
