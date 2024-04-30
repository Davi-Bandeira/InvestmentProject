package tech.investment.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.investment.project.dto.AccountDTO;
import tech.investment.project.dto.AccountStockDTO;
import tech.investment.project.dto.AccountStockRetrieve;
import tech.investment.project.entity.Account;
import tech.investment.project.entity.AccountStock;
import tech.investment.project.entity.AccountStockId;
import tech.investment.project.exception.NotFoundException;
import tech.investment.project.repository.AccountRepository;
import tech.investment.project.repository.AccountStockRepository;
import tech.investment.project.repository.StockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserService userService;
    private final StockRepository stockRepository;
    private final AccountRepository accountRepository;
    private final AccountStockRepository accountStockRepository;

    @Override
    @Transactional
    public void create(AccountDTO accountDTO) {
        var persistedUser = userService.findById(accountDTO.getUserId());

        var account = new Account();
        account.setUser(persistedUser);
        account.setDescription(accountDTO.getDescription());

        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void addStock(Long accountId, AccountStockDTO accountStockDTO) {
        var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Conta não encontrado"));
        var persistedStock = stockRepository.findById(accountStockDTO.getStockId())
                .orElseThrow(() -> new NotFoundException("Ação não encontrada"));
        var id = new AccountStockId(accountId, accountStockDTO.getStockId());
        var accountStock = new AccountStock(id, account, persistedStock, accountStockDTO);

        accountStockRepository.save(accountStock);
    }

    @Override
    public List<AccountStockRetrieve> listStocks(Long accountId) {
        var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Conta não encontrado"));

        return account.getAccountStocks().stream().map(accountStock ->
                        new AccountStockRetrieve(accountStock.getStock().getId(),
                                accountStock.getQuantity(),
                                accountStock.getTotalValue()))
                .toList();
    }
}
