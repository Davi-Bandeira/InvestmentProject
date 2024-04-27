package tech.investment.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.investment.project.dto.StockDTO;
import tech.investment.project.entity.Stock;
import tech.investment.project.repository.StockRepository;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public Stock create(StockDTO stockDTO) {
        var newStock = new Stock(stockDTO.getTicker(), stockDTO.getDescription());
        return stockRepository.save(newStock);
    }
}
