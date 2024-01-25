package hibernate.starter.hql;

import hibernate.starter.util.HibernateUtil;
import lombok.Cleanup;

/**
 * знакомство с HQL
 * HQL: Это язык запросов где User это таблица которая мапица на табличку "User",
 * Особенность HQL что мы опририруем классами, а не таблицами.
 * Так же можем делать параметризированные запросы, параметры можно
 * задавать двумя способами: ?n где "n" номер параматра, или аналогичным способом:
 * ":name" по названию.
 * так же можем использовать именнованные запросы: .createNamedQuery("getUser")
 * где в класса User с помощью анатации: @NamedQuery(name = "", query ="select u from User u where u.username = :name")
 * где "name" имя запроса(метода), "query" сам запрос на hql, ":name" параметр запроса.
 * так же можно использовать обычный SQL запрос с помощью метода .createNativeQuery("запрос", Name.class).executeUpdate();
 * c помощью HQL мы так же можем менять изменять структуру данных пример: UserFive
 * так же хочется добавить что параметризированные запросы мы можем использовать везде.
 */

public class HQL {
    public static void main(String[] args) {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();


        User user = hibernate.starter.hql.User.builder()
                .username("Aleksey")
                .build();
        session.merge(user);


        String name = "Aleksey";


        var users = session.createQuery
                (
                        "select u from User u where u.username = ?1"
                )
                .setParameter(1, name)
                .list();


        var usersTwo = session.createQuery
                        (
                                "select u from User u where u.username = :name"
                        )
                .setParameter("name", name)
                .list();


        var userThree = session.createNamedQuery("getUser")
                .setParameter("name", name)
                .list();


         var userFour = session.createNativeQuery
                (
                        "select * from users u where u.username = :name",
                        User.class
                )
                 .setParameter("name", name)
                 .list();

         session.createQuery
                 (
                         "update User u set username = 'HqlAndAleksey'"
                 ).executeUpdate();


        System.out.println(userFour);
        session.getTransaction().commit();
    }
}
