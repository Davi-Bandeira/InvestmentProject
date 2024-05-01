package tech.investment.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class StockRetrieve {

    private String id;

    private String name;

    private BigDecimal currentPrice;

    private String stockType;
}
