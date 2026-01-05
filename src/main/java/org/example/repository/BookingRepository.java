package org.example.repository;

import org.example.config.HibernateUtil;
import org.example.model.Booking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookingRepository {


    private final SessionFactory sessionFactory;

    public BookingRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Booking save(Booking booking) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(booking);
            tx.commit();
            return booking;
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

    public Optional<Booking> findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Booking.class, id));
        }
    }

    public List<Booking> findAll() {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM Booking", Booking.class).list();
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
                            "DELETE FROM Booking b WHERE b.id = :id")
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

    public void update(Booking exisiting) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Booking booking = session.find(Booking.class, exisiting.getId());
            if (booking != null) {
                booking.setUserId(exisiting.getUserId());
                booking.setTourId(exisiting.getTourId());
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

