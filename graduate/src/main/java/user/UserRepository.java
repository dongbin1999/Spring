package user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public Optional<UserEntity> findByLogin_id(String login_id);
}
