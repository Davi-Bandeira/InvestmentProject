package tech.investment.project.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.investment.project.dto.StockDTO;
import tech.investment.project.entity.Stock;
import tech.investment.project.service.StockService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
public class StockApi {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<Stock> create(@RequestBody StockDTO stockDTO) {
        var stock = stockService.create(stockDTO);
        return new ResponseEntity<>(stock, HttpStatus.CREATED);
    }
}
