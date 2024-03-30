package hibernate.starter.problem_n_plus_one.entity;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * так же что бы нормально работала корректно онатацыя @OneToMany
 * нужно или исключить из EqualsAndHashCode поле users что бы избежать
 * конфликта который вызовет ошибку стэкОверФлоу метод: exclude,
 * или переопределить метод: of, он делает так что бы EqualsAndHashCode
 * определялся только по полю данное поле должно быть уникальным.
 * Параматр: orphanRemoval = true В анатации: orphanRemoval = true
 * говорит о том что онатация будет следить за всеми изменениями
 * если у нас у юзера стоит: CascadeType.ALL то удолится и компания
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
//@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    /*
    анатацыя @Builder.Default создает дефолтную реализацыю коллекции SET
    иначе она не создастя и также мы ставим CascadeType.ALL как и в User
    для двухсторонней связи сохранения
     */
    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<>();


    public void addUser(User user) {
        users.add(user);
        user.setCompany(this);
    }
}
