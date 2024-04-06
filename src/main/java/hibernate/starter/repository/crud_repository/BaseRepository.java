package hibernate.starter.repository.crud_repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * managerFactory.getCurrentmanager(); возврощает сессию из настроек пропертиез:
 * <property name="hibernate.current_manager_context_class">thread</property>
 * есть три стратегии
 * thread: позволяет получить текущюю сесию и отерыть если ее нет
 * managed: просто получить сессию
 * таким образом мы можем не делать @ToString(exclude = {"company", "profile"})
 *
 * @param <K> ключь
 * @param <E> сущность
 */

@RequiredArgsConstructor
public class BaseRepository<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {

    private final Class<E> clazz;
    @Getter
    private final EntityManager manager;

    @Override
    public E save(E entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        manager.remove(manager.find(clazz, id));
        manager.flush();
    }

    @Override
    public void update(E entity) {
        manager.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(manager.find(clazz, id));
    }

    @Override
    public List<E> findAll() {

        var criteria = manager.getCriteriaBuilder().createQuery(clazz);
        criteria.from(clazz);
        return manager.createQuery(criteria).getResultList();
    }
}
