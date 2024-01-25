package hibernate.starter.criteriaAPI;


import hibernate.starter.criteriaAPI.dao.UserDao;
import hibernate.starter.criteriaAPI.model.User;
import hibernate.starter.util.HibernateUtil;
import lombok.Cleanup;

/**
 * критерии нужны для написания SQL запросов програмным языком
 * что бы упростить процесс была созданна библиотека hibernate-jpamodelgen
 * пример ее применения можно найти в методе userDao.findAllByFirstName()
 */
public class criteriaApiRunner {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = User.builder()
                .name("Aleksey")
                .build();
        session.merge(user);
        System.out.println(userDao.findAll(session));
        System.out.println(userDao.findAllByFirstName(session, "Aleksey"));
        session.getTransaction().commit();
    }
}
