package az.edu.turing.turingtinderapp.domain.repository;

import az.edu.turing.turingtinderapp.domain.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryDao extends UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    protected UserRepositoryDao(UserRepositoryDao userRepositoryDao) {
        super(userRepositoryDao);
    }

    @Override
    public List<User> findAllByIds(Set<Long> ids) {
        String query = "SELECT u FROM User u WHERE u.id IN :ids";
        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        typedQuery.setParameter("ids", ids);
        return typedQuery.getResultList();
    }


    @Override
    public List<User> getAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public List<User> getBy(Predicate<User> predicate) {
        return getAll().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public void insert(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }
}

