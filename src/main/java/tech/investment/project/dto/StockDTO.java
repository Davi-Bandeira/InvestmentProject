package tech.investment.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StockDTO {

    private String ticker;

    private String description;

    private double regularMarketPrice;
}
