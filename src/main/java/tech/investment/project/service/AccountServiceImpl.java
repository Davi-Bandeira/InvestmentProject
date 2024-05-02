package tech.investment.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.investment.project.dto.AccountDTO;
import tech.investment.project.dto.AccountStockDTO;
import tech.investment.project.dto.AccountStockRetrieve;
import tech.investment.project.entity.Account;
import tech.investment.project.exception.NotFoundException;
import tech.investment.project.mapper.AccountStockMapper;
import tech.investment.project.repository.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserService userService;
    private final AccountRepository accountRepository;
    private final AccountStockMapper accountStockMapper;
    private final AccountStockService accountStockService;

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
        var account = findById(accountId);
        accountStockService.addStock(account, accountStockDTO);
    }

    @Override
    public List<AccountStockRetrieve> listStocks(Long accountId) {
        var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Conta não encontrado"));

        return account.getAccountStocks().stream().map(accountStockMapper::fromEntity).toList();
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conta não encontrado"));
    }
}
