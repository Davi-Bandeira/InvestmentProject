package tech.investment.project.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.investment.project.dto.AccountDTO;
import tech.investment.project.dto.AccountStockDTO;
import tech.investment.project.dto.AccountStockRetrieve;
import tech.investment.project.service.AccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountApi {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AccountDTO accountDTO) {
        accountService.create(accountDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{id}/stocks")
    public ResponseEntity<Void> addStock(@PathVariable Long id, @RequestBody AccountStockDTO accountStockDTO) {
        accountService.addStock(id, accountStockDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}/stocks")
    public ResponseEntity<List<AccountStockRetrieve>> listStocks(@PathVariable Long id) {
        List<AccountStockRetrieve> stocks = accountService.listStocks(id);
        return ResponseEntity.ok(stocks);
    }
}
