package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import entity.Planet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HibernateSessionFactoryUtil;
import java.util.List;

public class PlanetDao implements  Dao {

    private static  final Logger LOGGER = LoggerFactory.getLogger(PlanetDao.class);

    public Planet findById(String id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Planet.class, id);
    }

    public Planet create(String planeId, String planetName) {
        return doInSession(s -> {
            Transaction tx1 = s.beginTransaction();
            Planet planet = new Planet(planeId, planetName);
            s.persist(planet);
            tx1.commit();
            return planet;
        });
    }

    public Planet update(Planet planet) {
        return doInSession(s -> {
            Transaction tx1 = s.beginTransaction();
            s.merge(planet);
            tx1.commit();
            return planet;
        });
    }

    public Boolean delete(Planet planet) {
        return doInSession(s -> {
            try {
                Transaction tx1 = s.beginTransaction();
                s.remove(planet);
                tx1.commit();
                return true;
            } catch (Exception e) {
                LOGGER.error("Planet deletion failed", e);
                return false;
            }

        });
    }
    public List<Planet> findAll() {
        return doInSession(s -> s.createQuery("FROM Planet", Planet.class).list());
    }

}
