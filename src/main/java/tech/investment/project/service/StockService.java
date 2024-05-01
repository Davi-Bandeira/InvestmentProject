package tech.investment.project.service;

import tech.investment.project.dto.StockDTO;
import tech.investment.project.dto.StockRetrieve;
import tech.investment.project.entity.Stock;

import java.util.List;

public interface StockService {

    StockRetrieve create(StockDTO stockDTO);

    List<StockRetrieve> findAll();

    Stock findById(String stockId);
}
