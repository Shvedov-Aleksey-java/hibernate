package hibernate.starter.blocked_session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "profile")
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * анатацыя @Audited создает отдельную таблицу где хранит версию изменения ид и такого рода данные
 * своего рода история изменений для этого была добавленна допалнительная зависимость в майвен
 * hibernate-envers
 */
@Audited
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "users_id")
    /**
     * анатацыя @NotAudited исключает поле из аудиту иначе в классе User тоже нужно поставить анатацыю аудит
     */
    @NotAudited
    private User user;
    @Column(name = "street")
    private String street;
    @Column(name = "language")
    private String language;

    public void addUser(User user) {
        this.user = user;
        user.setProfile(this);

    }

}
