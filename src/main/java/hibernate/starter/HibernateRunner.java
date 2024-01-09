package hibernate.starter;

import hibernate.starter.convertor.BirthDayConvertor;
import hibernate.starter.entity.BirthDay;
import hibernate.starter.entity.Role;
import hibernate.starter.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;


import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        // -- добовляем класс в хибернет
        //configuration.addAnnotatedClass(User.class);
        // -- хибернет будет воспринимать верблюжий шрифт как нижнее подчеркивание
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        // -- класс конвертор BirthDayConvertor() который конвертирует в свой собственный
        // -- класс аналогичный DataTime() и сохраняет в бд
        configuration.addAttributeConverter(new  BirthDayConvertor(), true);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = User.builder()
                    .username("Алексей")
                    .lastname("Владамирович")
                    .firstname("Шведов")
                    .birthDate(new BirthDay(LocalDate.of(1993, 9, 1)))
                    .role(Role.ADMIN)
                    .build();
            // -- новый метод save()
            //session.persist(user);
            // -- новый метод update()
            //user.setRole(Role.USER);
            //session.merge(user);
            // -- новый метод delete()
            //session.remove(user);
            //-- метод get() не поменялся
            //User userNew = session.get(User.class, "Алексей");
            session.getTransaction().commit();
            System.out.println("OK");
        }
    }
}
