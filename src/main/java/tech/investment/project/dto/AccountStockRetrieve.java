package tech.investment.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AccountStockRetrieve {

    private String stockId;
    private Integer quantity;
    private BigDecimal value;
}
