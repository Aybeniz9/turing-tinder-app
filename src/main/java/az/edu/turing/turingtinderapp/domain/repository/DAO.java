package az.edu.turing.turingtinderapp.domain.repository;

import az.edu.turing.turingtinderapp.domain.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public interface DAO<T> {
    List<User> findAllByIds(Set<Long> ids);
    List<T> getAll();

    List<T> getBy(Predicate<T> predicate);

    Optional<T> get(Long id);

    void insert(T t);

    void delete(Long id);

    void update(T t);
}
