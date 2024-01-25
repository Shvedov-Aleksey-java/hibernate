package hibernate.starter.mappedSuperClass;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * анатация: @MappedSuperclass позволяет вынести переменнные вне класса любые
 * @param <T>
 */

@Getter
@Setter
@MappedSuperclass
public abstract class MappedSuperClass<T extends Serializable, E> implements InterfaceMappedSuperClass<T, E> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;
    private E name;
    private Boolean gender;
}