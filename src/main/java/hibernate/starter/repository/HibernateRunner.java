package hibernate.starter.repository;

import hibernate.starter.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;

/**
 * мы создаем прокси сессию чтобы приложенние могло работать в многопоточном режиме
 */
public class HibernateRunner {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(Session.class.getClassLoader(),
                    new Class[]{Session.class},
                    ((proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1)));
            session.beginTransaction();
            UserRepository repository = new UserRepository(session);
        }
    }
}
