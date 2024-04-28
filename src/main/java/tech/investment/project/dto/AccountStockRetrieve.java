package tech.investment.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountStockRetrieve {

    private String stockId;
    private Integer quantity;
    private double value;
}
