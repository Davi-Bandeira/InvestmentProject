package tech.investment.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.investment.project.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {
}
