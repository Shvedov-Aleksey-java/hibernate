package hibernate.starter.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "profile")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "users_id")
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
