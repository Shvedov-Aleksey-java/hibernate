package hibernate.starter.blocked_session;


import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(exclude = "users")
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * анатацыя "@OptimisticLocking" с "type = OptimisticLockType" работает на уровне кода в поле версион вставляется число
 * с новой версией и когда происходит инсерт в бд так же учитывается и вхере на версион зачет чего второй кто попытается
 * обновить уже не сможет этого зделать
 * пример использованния в сессии:
 * var company = session.find(Company.class, 1L, LockModeType.OPTIMISTIC);
 *         company.setName("user");
 *         session.getTransaction().commit();
 */
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Company implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * стодбец version является в версией к нашей анатацыы "@OptimisticLocking"
     */
    @Version
    private Long version;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        users.add(user);
        user.setCompany(this);
    }
}
