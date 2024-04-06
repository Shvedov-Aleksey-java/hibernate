package hibernate.starter.repository.service;

import hibernate.starter.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public boolean delete(Long id) {
        var maybeUser = repository.findById(id);
        maybeUser.ifPresent(user -> repository.delete(id));
        return maybeUser.isPresent();
    }

}
