package tech.investment.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.investment.project.entity.AccountStock;
import tech.investment.project.entity.AccountStockId;

public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
