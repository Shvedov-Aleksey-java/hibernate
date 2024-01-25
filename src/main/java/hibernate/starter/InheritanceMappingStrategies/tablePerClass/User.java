package hibernate.starter.InheritanceMappingStrategies.tablePerClass;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * стратегия мипинга TABLE_PER_CLASS
 * анатация: @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 * не льзя использовать автогенерацию ключа strategy = GenerationType.IDENTITY,
 * можно только GenerationType.SEQUENCE.
 * плюсами данной стратегии является обращение к програмеру и менеджеру,
 * минусами то что дублируются поля при sql запросе, трудоемкий запрос.
 * так же таблица users не создается.
 */
@Data
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
}
