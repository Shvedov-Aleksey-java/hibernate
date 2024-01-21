package hibernate.starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * анатацыя @PrimaryKeyJoinColumn в данном случае альтернатива
 * JoinColumn(name = "users_id") здесь мы мапимся на колонку из
 * итой же таблицы.
 * В данном примере ключь ссылается на таблицу юзерс
 */

@Entity
@Data
@Table(name = "profile")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @Column(name = "users_id")
    private Long id;
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;
    @Column(name = "street")
    private String street;
    @Column(name = "language")
    private String language;

    public void addUser(User user) {
        this.user = user;
        id = user.getId();
        user.setProfile(this);

    }

}
