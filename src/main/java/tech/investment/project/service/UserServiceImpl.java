package tech.investment.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.investment.project.dto.UserDTO;
import tech.investment.project.dto.UserRetrieve;
import tech.investment.project.entity.User;
import tech.investment.project.exception.NotFoundException;
import tech.investment.project.mapper.UserMapper;
import tech.investment.project.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserRetrieve create(UserDTO userDTO) {
        var newUser = userMapper.fromDTO(userDTO);
        newUser = userRepository.save(newUser);
        return userMapper.fromEntity(newUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserRetrieve findRetriveById(Long id) {
        var persistedUser = findById(id);
        return userMapper.fromEntity(persistedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRetrieve> findAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(userMapper::fromEntity).toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var persistedUser = findById(id);
        userRepository.deleteById(persistedUser.getId());
    }

    @Override
    @Transactional
    public UserRetrieve update(Long id, UserDTO userDTO) {
        var persistedUser = findById(id);
        var updatedUser = userMapper.fromDTO(userDTO);
        updateData(persistedUser, updatedUser);
        return userMapper.fromEntity(persistedUser);
    }

    protected User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    private void updateData(User persistedUser, User updatedUser) {
        String[] ignoreProperties = List.of("id", "creationTimestamp", "updateTimestamp").toArray(new String[0]);
        BeanUtils.copyProperties(updatedUser, persistedUser, ignoreProperties);
    }
}
