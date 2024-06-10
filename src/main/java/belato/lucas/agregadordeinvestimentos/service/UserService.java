package belato.lucas.agregadordeinvestimentos.service;

import belato.lucas.agregadordeinvestimentos.Repository.AccountRepository;
import belato.lucas.agregadordeinvestimentos.Repository.BillingAddressRepository;
import belato.lucas.agregadordeinvestimentos.Repository.UserRepository;
import belato.lucas.agregadordeinvestimentos.controller.Dto.*;
import belato.lucas.agregadordeinvestimentos.entity.Account;
import belato.lucas.agregadordeinvestimentos.entity.BillingAddress;
import belato.lucas.agregadordeinvestimentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
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

    public void updateUserById(String userId, UpdateUserDto updateUsetDto) {
        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            var user = userEntity.get();
            System.out.println(user.getUsername());
            if (updateUsetDto.username() != null) {
                user.setUsername(updateUsetDto.username());
            }

            if (updateUsetDto.password() != null) {
                user.setPassword(updateUsetDto.password());
            }

            userRepository.save(user);
        }

    }

    public void createAccount(String userId, CreateAccountDto createAccountDto) {
            var user = userRepository.findById(UUID.fromString(userId))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            var account = new Account(
                    UUID.randomUUID(),
                    user,
                    null,
                    createAccountDto.description(),
                    new ArrayList<>()
            );

            var accountCreated = accountRepository.save(account);

            var billingAddress = new BillingAddress(
                    accountCreated.getAccountId(),
                    account,
                    createAccountDto.street(),
                    createAccountDto.number()
            );

            billingAddressRepository.save(billingAddress);
    }

    public List<AccountResponseDto> listAccounts(String userId) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return   user.getAccounts()
                .stream()
                .map(ac -> new AccountResponseDto(ac.getAccountId().toString(), ac.getDescription())).toList();


    }
}
