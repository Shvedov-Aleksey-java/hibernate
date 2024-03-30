package hibernate.starter.blocked_session;

import hibernate.starter.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 * было добавленно две зависимости в пум файл "ehcache" и  hibernate-jcache
 * для подтягивания второго кеша так же для настройка в hibernate.cfg.hml
 * были указанны следующие настройки <property name="hibernate.cache.use_second_level_cache">true</property>
 *     <property name="hibernate.cache.region_prefix.factory_class">org.hibernate.cache.jcache.internal.JCacheRegionFactory</property>
 * все энтити должны наследовать интерфэйс Serializable иначе не будет работать
 * второй кэш позволяет два одинаковых запроса в данной ситуации делается всего один запрос в бд
 * все сохроняется в кеше а потом берется из него
 * так же был в пропертиез был добавлен документ ehcache-config.xml который хранит две настройки для кеша
 * <ehcache:ttl>10</ehcache:ttl> сколько секунд будут хранится энтити
 * <ehcache:heap>1000</ehcache:heap> количество энтити которые будут хронится
 * что бы использовть эту настройку нужно в классе юзер в анатации @Cache указать region = "User"
 */
public class HibernateRunner {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var user = session.find(User.class, 1L);
        }
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var user = session.find(User.class, 1L);
        }
    }
}
