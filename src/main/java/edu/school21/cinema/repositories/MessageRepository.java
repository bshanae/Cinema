package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

@Repository
public class MessageRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Message> getLastMessages(int filmId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Message> criteriaQuery = cb.createQuery(Message.class);
        Root<Message> root = criteriaQuery.from(Message.class);

        criteriaQuery.select(root)
                     .where(cb.equal(root.get("filmId"), filmId))
                     .orderBy(cb.desc(root.get("sendTime")));

        Query<Message> query = session.createQuery(criteriaQuery);
        query.setMaxResults(20);

        List<Message> result = query.getResultList();
        Collections.reverse(result);

        return result;
    }

    public void saveMessage(Message message) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(message);
    }
}
