package dao;

import entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;
import java.sql.Timestamp;
import java.util.List;

public class TicketDao implements Dao{
    public Ticket findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Ticket.class, id);
    }

    public Ticket create(Timestamp timestamp, long clientId, String planetFrom, String planetTo) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Ticket ticket = new Ticket(timestamp, clientId, planetFrom, planetTo);
        session.persist(ticket);
        tx1.commit();
        session.close();
        return ticket;
    }

    public void update(Ticket ticket) {
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(ticket);
        tx1.commit();
        session.close();
    }

    public void delete(Ticket ticket) {
        Session s =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = s.beginTransaction();
        s.remove(ticket);
        tx1.commit();
        s.close();
    }

    public List<Ticket> findAll() {
        return doInSession(s -> s.createQuery("FROM Ticket", Ticket.class).list());
    }

    public List<Ticket> findClientTickets(long id) {
        String query = String.format("FROM Ticket WHERE client_id = %d", id);
        return doInSession(s -> s.createQuery(query, Ticket.class).list());
    }
}
