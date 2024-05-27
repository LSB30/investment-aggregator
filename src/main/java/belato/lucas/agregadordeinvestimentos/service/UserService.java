package belato.lucas.agregadordeinvestimentos.service;

import belato.lucas.agregadordeinvestimentos.Repository.UserRepository;
import belato.lucas.agregadordeinvestimentos.controller.CreateUserDto;
import belato.lucas.agregadordeinvestimentos.entity.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

        var entity = new User(UUID.randomUUID(),createUserDto.username(),createUserDto.email(), createUserDto.password(),Instant.now(),null);

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {
       return userRepository.findById(UUID.fromString(userId));
    }
}
