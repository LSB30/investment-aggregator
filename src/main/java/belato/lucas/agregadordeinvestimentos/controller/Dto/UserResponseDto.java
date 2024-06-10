package belato.lucas.agregadordeinvestimentos.controller.Dto;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDto(String userName, String email, String password, Instant creationTimestamp, Instant updateTimestamp, String accountId) {
}
