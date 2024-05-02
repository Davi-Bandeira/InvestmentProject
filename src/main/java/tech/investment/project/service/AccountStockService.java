package tech.investment.project.service;

import tech.investment.project.dto.AccountStockDTO;
import tech.investment.project.entity.Account;

public interface AccountStockService {

    void addStock(Account account, AccountStockDTO accountStockDTO);
}
