package hibernate.starter.repository;

import hibernate.starter.repository.crud_repository.BaseRepository;
import hibernate.starter.repository.model.User;

import javax.persistence.EntityManager;

public class UserRepository extends BaseRepository<Long, User> {
    public UserRepository(EntityManager sessionFactory) {
        super(User.class, sessionFactory);
    }
}
