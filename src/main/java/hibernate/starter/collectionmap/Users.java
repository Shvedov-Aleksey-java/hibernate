package hibernate.starter.collectionmap;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * анатация @MapKey(name = "pet_name") получаем мапу питомцов которая мапица
 * на имя питомца.
 * Имя питомца должно быть уникальным и не нулевым.
 */

@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @MapKey(name = "pet_name")
    private Map<String, Pet> usersMap = new HashMap<>();

}
