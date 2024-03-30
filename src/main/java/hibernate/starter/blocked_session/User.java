package hibernate.starter.blocked_session;

import hibernate.starter.entity.PersonalInfo;
import hibernate.starter.entity.Role;
import hibernate.starter.entity.UserChat;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Data
@Entity
@ToString(exclude = {"company", "profile"})
@EqualsAndHashCode(of = "id")
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Embedded
    private PersonalInfo personalInfo;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<UserChat> userChats = new ArrayList<>();
}
