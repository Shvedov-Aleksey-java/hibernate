package hibernate.starter.repository.crud_repository;

import java.io.Serializable;

public interface BaseEntity<T extends Serializable> {
    T getId();

    void setId(T id);
}
