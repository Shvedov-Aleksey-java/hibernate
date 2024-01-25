package hibernate.starter.criteriaAPI.dao;

import hibernate.starter.criteriaAPI.model.User;
import hibernate.starter.criteriaAPI.model.User_;
import org.hibernate.Session;

import java.util.List;


public class UserDao {

    public List<User> findAll(Session session) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user);

        return session.createQuery(criteria).list();
    }

    public List<User> findAllByFirstName(Session session, String firstName) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria
                .select(user)
                .where(cb.equal(user.get(User_.name), firstName))
                .orderBy(
                        cb.asc(user.get(User_.name))
                );
        return session.createQuery(criteria).list();
    }


}
