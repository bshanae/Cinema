package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class HallRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Hall> getHalls() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Hall> criteriaQuery = criteriaBuilder.createQuery(Hall.class);
        Root<Hall> root = criteriaQuery.from(Hall.class);
        criteriaQuery.select(root);

        Query<Hall> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public void addHall(Hall hall) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(hall);
    }
}
