package hibernate.starter;


import com.querydsl.core.Tuple;
import hibernate.starter.criteriaAPI.dao.UserDao;
import hibernate.starter.criteriaAPI.model.Company;
import hibernate.starter.criteriaAPI.model.User;
import hibernate.starter.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class QueryDslTest {

    @Test
    public void getAll() {
        UserDao userDao = new UserDao();
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = User.builder()
                .name("Aleksey")
                .build();
        Company company = Company.builder()
                .name("yandex")
                .build();
        session.save(company);
        user.setCompany(company);
        session.save(user);
        Assertions.assertEquals(userDao.findAll(session).get(0).getName(), "Aleksey");
        session.getTransaction().commit();
    }

    @Test
    public void findAllByCompanyName() {
        UserDao userDao = new UserDao();
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = User.builder()
                .name("Aleksey")
                .build();
        Company company = Company.builder()
                .name("yandex")
                .build();
        session.save(company);
        user.setCompany(company);
        session.save(user);
        Assertions.assertEquals(userDao.findAllByCompanyName(session, "yandex").get(0).getName(), "Aleksey");
        session.getTransaction().commit();
    }

    @Test
    public void findAllByFirstName() {
        UserDao userDao = new UserDao();
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = User.builder()
                .name("Aleksey")
                .build();
        Company company = Company.builder()
                .name("yandex")
                .build();
        session.save(company);
        user.setCompany(company);
        session.save(user);
        Assertions.assertEquals(userDao.findAllByFirstName(session, "Aleksey").get(0).getName(), "Aleksey");
        session.getTransaction().commit();
    }


    @Test
    public void predicateBuilder() {
        UserDao userDao = new UserDao();
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = User.builder()
                .name("Aleksey")
                .build();
        Company company = Company.builder()
                .name("yandex")
                .build();
        session.save(company);
        user.setCompany(company);
        session.save(user);
        Assertions.assertEquals(userDao.predicateBuilder(session, "yandex").get(0).getName(), "Aleksey");
        session.getTransaction().commit();
    }


    @Test
    public void isTuple() {
        UserDao userDao = new UserDao();
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = User.builder()
                .name("Aleksey")
                .build();
        Company company = Company.builder()
                .name("yandex")
                .build();
        session.save(company);
        user.setCompany(company);
        session.save(user);
        List<Tuple> tuples = userDao.isTuple(session);
        String name = tuples.get(0).get(0, String.class);
        String companyName = tuples.get(0).get(1, String.class);
        System.out.println(name);
        System.out.println(companyName);
        session.getTransaction().commit();
    }







}

