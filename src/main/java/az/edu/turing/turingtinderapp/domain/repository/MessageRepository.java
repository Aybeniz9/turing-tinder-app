package az.edu.turing.turingtinderapp.domain.repository;

import az.edu.turing.turingtinderapp.domain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
