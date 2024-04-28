package tech.investment.project.service;

import tech.investment.project.dto.AccountDTO;
import tech.investment.project.dto.AccountStockDTO;
import tech.investment.project.dto.AccountStockRetrieve;

import java.util.List;

public interface AccountService {

    void create(AccountDTO accountDTO);

    void addStock(Long id, AccountStockDTO accountStockDTO);

    List<AccountStockRetrieve> listStocks(Long id);
}
