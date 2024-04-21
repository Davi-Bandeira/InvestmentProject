package tech.investment.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRetrieve {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Instant creationTimestamp;

    private Instant updateTimestamp;
}
