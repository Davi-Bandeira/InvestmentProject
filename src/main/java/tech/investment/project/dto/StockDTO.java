package tech.investment.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tech.investment.project.constraint.UniqueStockId;

@Getter
@Setter
@AllArgsConstructor
public class StockDTO {

    @UniqueStockId
    private String ticker;

    private String stockType;
}
