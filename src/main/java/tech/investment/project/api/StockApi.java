package tech.investment.project.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.investment.project.dto.StockDTO;
import tech.investment.project.dto.StockRetrieve;
import tech.investment.project.service.StockService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
public class StockApi {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockRetrieve> create(@Valid @RequestBody StockDTO stockDTO) {
        var stock = stockService.create(stockDTO);
        return new ResponseEntity<>(stock, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StockRetrieve>> findAll() {
        var stocks = stockService.findAll();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }
}
