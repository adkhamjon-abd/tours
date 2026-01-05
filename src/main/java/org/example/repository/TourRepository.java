package org.example.repository;

import org.example.model.Tour;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TourRepository {

    private final SessionFactory sessionFactory;

    public TourRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Tour save(Tour tour){
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(tour);
            tx.commit();
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
        return tour;
    }

    public Optional<Tour> findById(int id){
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Tour.class, id));
        }
    }

    public List<Tour> findAll(){
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            List<Tour> tours = session.createQuery("FROM Tour", Tour.class).list();
            tx.commit();
            return tours;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void deleteTourById(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.createQuery(
                            "DELETE FROM Tour t WHERE t.id = :id")
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

    public void update(Tour tour){
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Tour existing = session.find(Tour.class, tour.getId());

            if (existing !=null){
                existing.setName(tour.getName());
                existing.setCompanyId(tour.getCompanyId());
            }

            tx.commit();
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
}
