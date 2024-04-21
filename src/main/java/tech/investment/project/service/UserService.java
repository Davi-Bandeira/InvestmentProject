package tech.investment.project.service;

import tech.investment.project.dto.UserDTO;
import tech.investment.project.dto.UserRetrieve;

import java.util.List;

public interface UserService {

    UserRetrieve create(UserDTO userDTO);

    UserRetrieve findRetriveById(Long id);

    List<UserRetrieve> findAll();

    void deleteById(Long id);

    UserRetrieve update(Long id, UserDTO userDTO);
}
