package az.edu.turing.turingtinderapp.domain.repository;

import az.edu.turing.turingtinderapp.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
