package hibernate.starter.newLessonOne.One;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * анатация @MappedSuperclass используется для простых вещей что бы вынести
 * поля вне класса
 * @param <T>
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity<T extends Serializable> implements BaseEntity<T> {
    private String name;
    private Boolean gender;
}
