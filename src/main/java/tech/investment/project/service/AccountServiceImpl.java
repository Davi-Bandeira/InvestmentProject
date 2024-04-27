package tech.investment.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.investment.project.dto.AccountDTO;
import tech.investment.project.entity.Account;
import tech.investment.project.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserService userService;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void create(AccountDTO accountDTO) {
        var persistedUser = userService.findById(accountDTO.getUserId());

        var account = new Account();
        account.setUser(persistedUser);
        account.setDescription(accountDTO.getDescription());

        accountRepository.save(account);
    }
}
