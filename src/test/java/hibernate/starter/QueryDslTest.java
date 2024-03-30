package hibernate.starter;


import hibernate.starter.criteriaAPI.dao.UserDao;
import hibernate.starter.criteriaAPI.model.Company;
import hibernate.starter.criteriaAPI.model.NameAll;
import hibernate.starter.criteriaAPI.model.User;
import hibernate.starter.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.LockModeType;
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
        List<NameAll> nameAll = userDao.isTuple(session).stream()
                .map(o1 -> new NameAll(o1.get(0, String.class), o1.get(1, String.class)))
                .collect(Collectors.toList());
        System.out.println(nameAll.get(0));
        session.getTransaction().commit();
    }







}

