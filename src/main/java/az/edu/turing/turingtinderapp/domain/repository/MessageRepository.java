package az.edu.turing.turingtinderapp.domain.repository;

import az.edu.turing.turingtinderapp.domain.entity.Message;
import az.edu.turing.turingtinderapp.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

@Repository
public class MessageRepository implements DAO<Message>{
    @Override
    public List<User> findAllByIds(Set<Long> ids) {
        return List.of();
    }//silecem sonra

    @Override
    public List<Message> getAll() {
        return List.of();
    }

    @Override
    public List<Message> getBy(Predicate<Message> predicate) {
        return List.of();
    }

    @Override
    public Optional<Message> get(Long id) {
        return Optional.empty();
    }

    @Override
    public void insert(Message message) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Message message) {

    }
}
