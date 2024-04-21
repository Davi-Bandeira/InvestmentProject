package tech.investment.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.investment.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
