package tech.investment.project.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.investment.project.dto.AccountDTO;
import tech.investment.project.service.AccountService;

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
}
