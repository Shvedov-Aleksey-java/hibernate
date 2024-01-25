package hibernate.starter.InheritanceMappingStrategies.joined;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * стратегия мипинга JOINED
 * анатация: @Inheritance(strategy = InheritanceType.JOINED)
 * Плюсами данной стратегии является: соответствие нормализации бызы данных
 * Минусами данной стратегии является: невысокая производительность,
 * используется не очень часто.
 */
@Data
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
}
