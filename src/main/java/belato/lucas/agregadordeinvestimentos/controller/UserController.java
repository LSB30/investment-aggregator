package belato.lucas.agregadordeinvestimentos.controller;

import belato.lucas.agregadordeinvestimentos.controller.Dto.AccountResponseDto;
import belato.lucas.agregadordeinvestimentos.controller.Dto.CreateAccountDto;
import belato.lucas.agregadordeinvestimentos.controller.Dto.CreateUserDto;
import belato.lucas.agregadordeinvestimentos.controller.Dto.UpdateUserDto;
import belato.lucas.agregadordeinvestimentos.entity.User;
import belato.lucas.agregadordeinvestimentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody CreateUserDto createUserDto) {
            var userId = userService.createUser(createUserDto);
            return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User>getUserById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);

        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        var users = userService.listUser();

        return ResponseEntity.ok(users);
    }


    @PutMapping("{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,@RequestBody UpdateUserDto updateUsetDto) {
        userService.updateUserById(userId, updateUsetDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void>createAccount(@PathVariable("userId") String userId, CreateAccountDto createAccountDto ) {

        userService.createAccount(userId,createAccountDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDto>>listAccounts(@PathVariable("userId") String userId ) {

        var accounts = userService.listAccounts(userId);

        return ResponseEntity.ok(accounts);
    }


}
