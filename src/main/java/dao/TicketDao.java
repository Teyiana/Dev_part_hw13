package dao;

import entity.Client;
import entity.Planet;
import entity.Ticket;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HibernateSessionFactoryUtil;
import java.sql.Timestamp;
import java.util.List;

public class TicketDao implements Dao {

    private static  final Logger LOGGER = LoggerFactory.getLogger(PlanetDao.class);



    public Ticket findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Ticket.class, id);
    }

    public Ticket create(Timestamp timestamp, Client client, Planet planetFrom, Planet planetTo) {
        return doInSession(s -> {
            Transaction tx1 = s.beginTransaction();
            Ticket ticket = new Ticket(timestamp, client, planetFrom, planetTo);
            s.persist(ticket);
            tx1.commit();
            return ticket;
        });
    }

    public Ticket update(Ticket ticket) {
        return doInSession(s -> {
            Transaction tx1 = s.beginTransaction();
            s.merge(ticket);
            tx1.commit();
            return ticket;
        });
    }

    public Boolean delete(Ticket ticket) {
        return doInSession(s -> {
            try{
                Transaction tx1 = s.beginTransaction();
                s.remove(ticket);
                tx1.commit();
                return true;
            }catch (Exception e) {
                LOGGER.error("Ticket deletion failed", e);
                return false;
            }

        });
    }

    public List<Ticket> findAll() {
        return doInSession(s -> s.createQuery("FROM Ticket", Ticket.class).list());
    }
}
