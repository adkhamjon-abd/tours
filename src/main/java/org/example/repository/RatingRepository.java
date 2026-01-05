package org.example.repository;

import org.example.model.Rating;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RatingRepository {

    private final SessionFactory sessionFactory;

    public RatingRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Rating save(Rating rating){

        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(rating);
            tx.commit();
            return rating;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }


    }

    public List<Rating> findAll(){
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM Rating", Rating.class).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Optional<Rating> findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Rating.class, id));
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

        }catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(Rating updateRating){
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Rating rating = session.find(Rating.class, updateRating.getId());
            if (rating != null) {
                rating.setUserId(updateRating.getUserId());
                rating.setTourId(updateRating.getTourId());
                rating.setScore(updateRating.getScore());
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
