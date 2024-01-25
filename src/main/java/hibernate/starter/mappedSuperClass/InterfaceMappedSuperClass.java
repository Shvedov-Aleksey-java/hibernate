package hibernate.starter.mappedSuperClass;

import java.io.Serializable;

public interface InterfaceMappedSuperClass<T extends Serializable, E> {
    T getId();
    void setId(T id);

    E getName();
    void setName(E name);

}
