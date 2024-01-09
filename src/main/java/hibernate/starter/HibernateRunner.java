package hibernate.starter;

import hibernate.starter.entity.BirthDay;
import hibernate.starter.entity.Role;
import hibernate.starter.entity.User;
import hibernate.starter.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        //TRANSIENT
        User user = User.builder()
                .username("Алексей")
                .lastname("Владамирович")
                .firstname("Шведов")
                .birthDate(new BirthDay(LocalDate.of(1993, 9, 1)))
                .role(Role.ADMIN)
                .build();
        //TRANSIENT
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try(Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                //PERSISTENT к session1 и TRANSIENT к session2
                session1.persist(user);
                session1.getTransaction().commit();
            }
            try(Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();

                //Сначала GET потом DELETE PERSISTENT к session2 DETACHED к session1
                session2.remove(user);

                //REMOVED к session2
                session2.getTransaction().commit();
            }
        }
    }
}
