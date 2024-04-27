package tech.investment.project.service;

import tech.investment.project.dto.StockDTO;
import tech.investment.project.entity.Stock;

public interface StockService {

    Stock create(StockDTO stockDTO);
}
