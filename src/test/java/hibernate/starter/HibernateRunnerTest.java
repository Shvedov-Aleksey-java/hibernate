package hibernate.starter;

import hibernate.starter.entity.*;
import hibernate.starter.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * анатацыя @Cleanup принадлежит ламбуку и заменяет try with resources
 * в сессии и автоматически закрывает сессию.
 * Тест checkOrphalRemoval() показывает как удолить юзера из компании
 * и главное убрать из юзера Cascade = CascadeType.ALL иначе удалится
 * компания
 */
public class HibernateRunnerTest {

    @Test
    public void checkManyToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Chat chat = Chat.builder()
                .name("chat")
                .build();
        User user = User.builder()
                .username("Aleksey")
                .build();
        User user1 = session.merge(user);
        user1.addChat(chat);
        session.persist(chat);
        session.getTransaction().commit();
    }

    @Test
    public void checkOneToOne() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = User.builder()
                .username("Aleksey")
                .build();
        Profile profile = Profile.builder()
                .street("lenina 25")
                .language("RU")
                .build();
        session.merge(user);
        profile.addUser(user);
        session.persist(profile);
        session.getTransaction().commit();
    }

    @Test
    public void checkOrphalRemoval() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var company = session.get(Company.class, 4);
        company.getUsers().removeIf(user -> user.getId() == 6);
        session.getTransaction().commit();
    }

    @Test
    public void checkOneToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var company = session.get(Company.class, 3);
        System.out.println(company.getUsers());
        session.getTransaction().commit();
    }

    @Test
    public void addUser() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Company company = Company.builder()
                .name("yandex")
                .build();
        User user = User.builder()
                .username("Aleksey")
                .personalInfo(
                        PersonalInfo.builder()
                                .lastname("Владамирович")
                                .firstname("Шведов")
                                .birthDate(new BirthDay(LocalDate.of(1993, 9, 1)))
                                .build()
                )
                .role(Role.ADMIN)
                .company(company)
                .build();
        company.addUser(user);
        session.persist(company);
        session.getTransaction().commit();
    }
}
