package tech.investment.project.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.investment.project.dto.AccountRetrieve;
import tech.investment.project.dto.UserDTO;
import tech.investment.project.dto.UserRetrieve;
import tech.investment.project.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApi {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRetrieve> create(@RequestBody UserDTO userDTO) {
        UserRetrieve userRetrieve = userService.create(userDTO);
        return new ResponseEntity<>(userRetrieve, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRetrieve> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findRetrieveById(id));
    }

    @GetMapping
    public List<UserRetrieve> findAllActive() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRetrieve> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserRetrieve updatedUser = userService.update(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/accounts")
    public List<AccountRetrieve> findByUser(@PathVariable Long id) {
        return userService.findByUser(id);
    }
}
