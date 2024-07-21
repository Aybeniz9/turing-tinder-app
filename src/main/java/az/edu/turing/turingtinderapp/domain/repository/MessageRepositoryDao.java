package az.edu.turing.turingtinderapp.domain.repository;
import az.edu.turing.turingtinderapp.domain.entity.Message;
import az.edu.turing.turingtinderapp.domain.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class MessageRepositoryDao implements DAO<Message> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAllByIds(Set<Long> ids) {
        return List.of();
    }
//silecem sonra
    @Override
    public List<Message> getAll() {
        return entityManager.createQuery("FROM Message", Message.class).getResultList();
    }

    @Override
    public List<Message> getBy(Predicate<Message> predicate) {
        return getAll().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Optional<Message> get(Long id) {
        return Optional.ofNullable(entityManager.find(Message.class, id));
    }

    @Override
    @Transactional
    public void insert(Message message) {
        entityManager.persist(message);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Message message = entityManager.find(Message.class, id);
        if (message != null) {
            entityManager.remove(message);
        }
    }

    @Override
    @Transactional
    public void update(Message message) {
        entityManager.merge(message);
    }
}
