package az.edu.turing.turingtinderapp.domain.repository;

import az.edu.turing.turingtinderapp.domain.entity.Like;
import az.edu.turing.turingtinderapp.domain.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class LikeRepositoryDao extends LikeRepository{
    @Override
    public List<User> findAllByIds(Set<Long> ids) {
        return List.of();
    } //silecem sonra

    @Override
    public List<Like> getAll() {
        return List.of();
    }

    @Override
    public List<Like> getBy(Predicate<Like> predicate) {
        return List.of();
    }

    @Override
    public Optional<Like> get(Long id) {
        return Optional.empty();
    }

    @Override
    public void insert(Like like) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Like like) {

    }
}
