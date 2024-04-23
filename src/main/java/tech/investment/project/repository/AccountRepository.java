package tech.investment.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.investment.project.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
