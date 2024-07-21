package az.edu.turing.turingtinderapp.domain.repository;

import az.edu.turing.turingtinderapp.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public abstract class UserRepository implements DAO<User> {
    private final UserRepositoryDao userRepositoryDao;

    protected UserRepository(UserRepositoryDao userRepositoryDao) {
        this.userRepositoryDao = userRepositoryDao;
    }


    @Override
    public List<User> getAll() {
        return userRepositoryDao.getAll();
    }

    @Override
    public List<User> getBy(Predicate<User> predicate) {
        return userRepositoryDao.getAll().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> get(Long id) {
        return userRepositoryDao.get(id);
    }

    @Override
    public void insert(User user) {
        userRepositoryDao.insert(user);
    }

    @Override
    public void delete(Long id) {
        userRepositoryDao.delete(id);
    }

    @Override
    public void update(User user) {
        userRepositoryDao.update(user);
    }
}
