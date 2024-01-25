package hibernate.starter.InheritanceMappingStrategies.singleTable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * стратегия мипинга SINGLE_TABLE
 * анатация: @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
 * данная стратегия создает одну таблицу для всех кроме юзера, в
 * этой таблицы должна быть колонка отвечающая за ее тип она так же
 * указываеься в родительском классе с помощью анатации: @DiscriminatorColumn(name = "type"),
 * где (name = "type") название колонки. Так же в классах наследниках,
 * с помощью анатации @DiscriminatorValue("name"), мы указываем значение
 * этой колонки, где ("name") : это имя как будет записанно это поле.
 * Минусами данной стратегии является: несоответствует нормализации бызы данных.
 * Плюсами данной стратегии является: высокая производительность за счет чего эту стратегию
 * используют чаще всего
 */
@Data
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
}
