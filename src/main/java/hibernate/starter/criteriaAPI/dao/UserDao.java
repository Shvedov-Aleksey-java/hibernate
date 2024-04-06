package hibernate.starter.criteriaAPI.dao;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import hibernate.starter.criteriaAPI.QPredicate;
import hibernate.starter.criteriaAPI.model.User;
import org.hibernate.Session;

import java.util.List;

import static hibernate.starter.criteriaAPI.model.QCompany.company;
import static hibernate.starter.criteriaAPI.model.QUser.user;



public class UserDao {

    public List<User> findAll(Session session) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .fetch();
    }

    public List<User> findAllByFirstName(Session session, String firstName) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(user.name.eq(firstName))
                .fetch();
    }

    public List<User> findAllByCompanyName(Session session, String companyName) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(user.company().name.eq(companyName))
                .fetch();
    }

    public List<User> findLimitedOrderByBirthDate(Session session, int limit) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .orderBy(new OrderSpecifier<>(Order.ASC, user.birthDay))
                .limit(limit)
                .fetch();
    }

    /**
     * Демонстрация класса QPredicate
     */
    public List<User> predicateBuilder(Session session, String companyName) {
        var predicate = QPredicate.builder().add(companyName, user.company().name::containsIgnoreCase).buildAnd();
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }

    /**
     *  класс Tuple сохраняет возврощаемммые обьекты в виде своего рода мапы
     *  где tuple.get(size) это возврощает список обьектов из селекта
     *  a tuple.get(size).get(size) это номер возврощаемого обьекта 0 вым будет
     *  user.name, 1 вым бедет company.name
     *  данная реализация созданна для решения получения данных для нестондаотных запросов
     */
    public List<Tuple> isTuple(Session session) {
        return new JPAQuery<Tuple>(session)
                .select(user.name , company.name)
                .from(user)
                .join(user.company(), company)
                .fetch();
    }


}
