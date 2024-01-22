package hibernate.starter.newLessonOne.One;

import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;



public interface BaseEntity<T extends Serializable> {
    T getId();

    void setId(T id);
}
