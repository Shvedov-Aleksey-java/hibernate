package hibernate.starter;

import hibernate.starter.entity.*;
import hibernate.starter.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

/**
 * для корректной работы OneToMany, ManyToOne мы должны исключать переменные класса
 * (над ними именно ставятся онотации) из методов ToString, Equals, HashCode это можно
 * зделать с помощью анатацый: @EqualsAndHashCode(exclude = "users"), @ToString(exclude = "users")
 * как альтернотивный вариант мы можем переопределить Equals и HashCode, что бы два
 * этих метода определялись только по одному полю на данное поле должно быть уникальным
 * анатацыя: @EqualsAndHashCode(of = "users")
 */

@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        //TRANSIENT
        Company company = Company.builder()
                .name("google")
                .build();
        User user = User.builder()
                .username("Алексей")
                .personalInfo(
                        PersonalInfo.builder()
                                .lastname("Владамирович")
                                .firstname("Шведов")
                                .build()
                )
                .role(Role.ADMIN)
                .company(company)
                .build();
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try(Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();
                session1.persist(user);
                //User user1 = session1.get(User.class, 4);
                //session1.remove(user1);
                session1.getTransaction().commit();
            } catch (Exception e) {
                log.error("Exception occurred: ", e);
                throw e;
            }
        }
    }
}

