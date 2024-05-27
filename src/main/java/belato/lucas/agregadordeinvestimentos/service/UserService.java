package belato.lucas.agregadordeinvestimentos.service;

import belato.lucas.agregadordeinvestimentos.Repository.UserRepository;
import belato.lucas.agregadordeinvestimentos.controller.CreateUserDto;
import belato.lucas.agregadordeinvestimentos.controller.UpdateUsetDto;
import belato.lucas.agregadordeinvestimentos.entity.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {

        System.out.println(createUserDto);

        var entity = new User(UUID.randomUUID(), createUserDto.username(), createUserDto.email(), createUserDto.password(), Instant.now(), null);

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUser() {
        return userRepository.findAll();
    }

    public void deleteUserById(String userId) {
        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }

    public void UpdateUserById(String userId, UpdateUsetDto updateUsetDto) {
        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);
        System.out.println(userEntity);
        if (userEntity.isPresent()) {
            var user = userEntity.get();
            System.out.println(user.getUsername());
            if (updateUsetDto.username() != null) {
                user.setUsername(updateUsetDto.username());
            }
            System.out.println(user.getUsername());

            System.out.println(user.getPassword());
            if (updateUsetDto.password() != null) {
                user.setPassword(updateUsetDto.password());
            }
            System.out.println(user.getPassword());

            userRepository.save(user);
        }

    }
}
