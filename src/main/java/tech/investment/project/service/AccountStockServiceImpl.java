package tech.investment.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.investment.project.dto.AccountStockDTO;
import tech.investment.project.entity.Account;
import tech.investment.project.entity.AccountStock;
import tech.investment.project.entity.AccountStockId;
import tech.investment.project.repository.AccountStockRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountStockServiceImpl implements AccountStockService {

    private final StockService stockService;
    private final AccountStockRepository accountStockRepository;

    @Override
    @Transactional
    public void addStock(Account account, AccountStockDTO accountStockDTO) {
        var id = new AccountStockId(account.getId(), accountStockDTO.getStockId());
        var accountStock = findById(id);

        if (accountStock == null) {
            accountStock = buildNewAccountStock(id, account, accountStockDTO);
            account.getAccountStocks().add(accountStock);
        } else {
            updateAccountStock(accountStock, accountStockDTO);
            updateAccountStockList(account.getAccountStocks(), accountStock);
        }
        updateAccountAfterAddStock(account, accountStock);
    }

    @Override
    public void updateWalletBalancing(Account account) {
        List<AccountStock> stocks = account.getAccountStocks();
        stocks.forEach(stock -> calcAccountStockPercentWallet(account, stock));
    }

    public AccountStock findById(AccountStockId id) {
        return accountStockRepository.findById(id)
                .orElse(null);
    }

    private AccountStock buildNewAccountStock(AccountStockId id, Account account, AccountStockDTO accountStockDTO) {
        var stock = stockService.findById(id.getStockId());
        return new AccountStock(id, account, stock, accountStockDTO);
    }

    public void updateAccountStock(AccountStock accountStock, AccountStockDTO accountStockDTO) {
        calcQuantity(accountStock, accountStockDTO);
        calcTotalCost(accountStock, accountStockDTO);
        calcAvgPrice(accountStock);
        calcTotalValue(accountStock);
    }

    private void calcQuantity(AccountStock accountStock, AccountStockDTO accountStockDTO) {
        var oldQuantity = accountStock.getQuantity();
        var newQuantity = accountStockDTO.getQuantity();
        var totalQuantity = oldQuantity + newQuantity;

        accountStock.setQuantity(totalQuantity);
    }

    private void calcTotalCost(AccountStock accountStock, AccountStockDTO accountStockDTO) {
        var oldCost = accountStock.getTotalCost();
        var quantity = BigDecimal.valueOf(accountStockDTO.getQuantity());
        var unitPrice = accountStockDTO.getUnitPrice();

        var newCost = oldCost.add(quantity.multiply(unitPrice));
        accountStock.setTotalCost(newCost);
    }

    private void calcAvgPrice(AccountStock accountStock) {
        var totalCost = accountStock.getTotalCost();
        var quantity = BigDecimal.valueOf(accountStock.getQuantity());

        var avgPrice = totalCost.divide(quantity, 2, RoundingMode.HALF_DOWN);
        accountStock.setAvgPrice(avgPrice);
    }

    private void calcTotalValue(AccountStock accountStock) {
        var quantity = BigDecimal.valueOf(accountStock.getQuantity());
        var currentPrice = accountStock.getStock().getCurrentPrice();

        var totalValue = quantity.multiply(currentPrice);
        accountStock.setTotalValue(totalValue);
    }

    private void updateAccountAfterAddStock(Account account, AccountStock accountStock) {
        if (account.getAccountStocks().isEmpty()) {
            account.setTotalCost(accountStock.getTotalCost());
            account.setTotalValue(accountStock.getTotalValue());
            accountStock.setPercentWallet(BigDecimal.valueOf(100));
        } else {
            calcAccountTotalCost(account, accountStock);
            calcAccountTotalValue(account, accountStock);
        }
    }

    private void calcAccountTotalCost(Account account, AccountStock accountStock) {
        var oldCost = account.getTotalCost();
        var costAdded = accountStock.getTotalCost();

        var newCost = oldCost.add(costAdded);
        account.setTotalCost(newCost);
    }

    private void calcAccountTotalValue(Account account, AccountStock accountStock) {
        var oldValue = account.getTotalValue();
        var valueAdded = accountStock.getTotalValue();

        var newValue = oldValue.add(valueAdded);
        account.setTotalValue(newValue);
    }

    private void calcAccountStockPercentWallet(Account account, AccountStock accountStock) {
        var accountTotalValue = account.getTotalValue();
        var stockTotalValue = accountStock.getTotalValue();

        var percentWallet = stockTotalValue.multiply(BigDecimal.valueOf(100))
                .divide(accountTotalValue, 2, RoundingMode.HALF_DOWN);
        accountStock.setPercentWallet(percentWallet);
    }

    private void updateAccountStockList(List<AccountStock> accountStocks, AccountStock updatedStock) {
        for (int i = 0; i < accountStocks.size(); i++) {
            AccountStock stock = accountStocks.get(i);
            if (stock.getId().equals(updatedStock.getId())) {
                accountStocks.set(i, updatedStock);
                return;
            }
        }
    }
}
