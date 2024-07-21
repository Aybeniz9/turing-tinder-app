package az.edu.turing.turingtinderapp.domain.repository;

import az.edu.turing.turingtinderapp.domain.entity.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class UserRepositoryDao extends UserRepository {

    Connection connection=new JdbcConnect().getConnection();

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public List<User> getBy(Predicate<User> predicate) {
        return List.of();
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.empty();
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(User user) {

    }
}
