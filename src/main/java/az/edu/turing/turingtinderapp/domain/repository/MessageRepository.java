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
public interface MessageRepository extends JpaRepository<Message, Long> {

}
