package tech.investment.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.investment.project.client.BrapiClient;
import tech.investment.project.dto.StockDTO;
import tech.investment.project.dto.StockRetrieve;
import tech.investment.project.entity.Stock;
import tech.investment.project.mapper.StockMapper;
import tech.investment.project.repository.StockRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    @Value("#{environment.TOKEN}")
    private String TOKEN;

    private final BrapiClient brapiClient;
    private final StockMapper stockMapper;
    private final StockRepository stockRepository;

    @Override
    @Transactional
    public StockRetrieve create(StockDTO stockDTO) {
        var newStock = stockMapper.fromDTO(stockDTO);
        newStock = updateToCurrentValues(newStock);
        newStock = stockRepository.save(newStock);
        return stockMapper.fromEntity(newStock);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockRetrieve> findAll() {
        var stocks = stockRepository.findAll();
        return stocks.stream().map(stockMapper::fromEntity).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Stock findById(String stockId) {
        return stockRepository.findById(stockId)
                .orElse(null);
    }

    private Stock updateToCurrentValues(Stock newStock) {
        var response = brapiClient.getQuote(TOKEN, newStock.getId());
        var stock = response.results().getFirst();
        return new Stock(newStock.getId(), stock.getLongName(),
                BigDecimal.valueOf(stock.getRegularMarketPrice()), newStock.getStockType());
    }
}
