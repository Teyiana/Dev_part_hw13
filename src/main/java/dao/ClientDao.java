package dao;

import org.hibernate.Transaction;
import entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HibernateSessionFactoryUtil;
import java.util.List;

public class ClientDao implements Dao{
    private static  final Logger LOGGER = LoggerFactory.getLogger(ClientDao.class);

    public Client findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, id);
    }

    public Client create(String clientName) {
        return doInSession(s -> {
            Transaction tx1 = s.beginTransaction();
            Client client = new Client(clientName);
            s.persist(client);
            tx1.commit();
            return client;
        });
    }

    public Client update(Client client) {
        return doInSession(s -> {
            Transaction tx1 = s.beginTransaction();
            s.merge(client);
            tx1.commit();
            return client;
        });
    }

    public Boolean delete(Client client) {
        return doInSession(s -> {
            try{
                Transaction tx1 = s.beginTransaction();
                s.remove(client);
                tx1.commit();
                return true;
            }catch (Exception e) {
                LOGGER.error("Client deletion failed", e);
                return false;
            }
        });
    }

    public List<Client> findAll() {
        return doInSession(s -> s.createQuery("FROM Client", Client.class).list());
    }
}
