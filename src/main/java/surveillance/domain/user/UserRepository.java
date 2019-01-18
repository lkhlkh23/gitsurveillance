package surveillance.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySlackId(String slackId);

    Optional<User> findByUserId(String userId);
}
