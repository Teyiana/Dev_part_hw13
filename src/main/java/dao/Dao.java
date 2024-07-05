package dao;

import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

import java.util.function.Function;

public interface Dao {

    default <T> T doInSession(Function<Session, T> toDo) {
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            return toDo.apply(session);
        }
    }
}
