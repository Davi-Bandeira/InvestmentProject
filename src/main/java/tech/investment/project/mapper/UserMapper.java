package tech.investment.project.mapper;

import tech.investment.project.dto.UserDTO;
import tech.investment.project.dto.UserRetrieve;
import tech.investment.project.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromDTO(UserDTO userDTO);

    UserRetrieve fromEntity(User user);
}

