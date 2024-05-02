package tech.investment.project.dto;

import java.math.BigDecimal;

public record AccountStockRetrieve(
        String stockId,
        Integer quantity,
        BigDecimal avgPrice,
        BigDecimal currentPrice,
        BigDecimal percentWallet,
        BigDecimal totalCost,
        BigDecimal totalValue
) {}