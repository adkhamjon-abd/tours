package org.example.repository;

import org.example.config.HibernateUtil;
import org.example.model.Rating;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RatingRepository {

    private final Map<Integer, Rating> ratings = new HashMap<>();
    private int nextId = 6;

    public RatingRepository(TourRepository tourRepository, UserRepository userRepository) {
        ratings.put(1, new Rating(1, 1, 1, 4));
        ratings.put(2, new Rating(2, 2, 1, 3));
        ratings.put(3, new Rating(3, 3, 1, 4));
        ratings.put(4, new Rating(4, 4, 1, 5));
        ratings.put(5, new Rating(5, 5, 1, 5));

    }

    public Rating save(Rating rating){

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Rating", Rating.class).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Optional<Rating> findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.find(Rating.class, id));
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

    public void update(Rating updateRating){
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
