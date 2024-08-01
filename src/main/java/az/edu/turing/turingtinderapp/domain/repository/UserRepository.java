package az.edu.turing.turingtinderapp.domain.repository;

import az.edu.turing.turingtinderapp.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContaining(String name);

    List<User> findByPhotoUrl(String photoUrl);

    List<User> findByLastLogin(LocalDate lastLogin);

    @Query("SELECT u.likedUserIds FROM User u WHERE u.id = :userId")
    List<Long> findLikedUserIdsByUserId(@Param("userId") Long userId);
}
