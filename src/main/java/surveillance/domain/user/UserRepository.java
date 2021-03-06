package surveillance.domain.user;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByCommitted(boolean deleted);

    List<User> findAll(Sort sort);

}
