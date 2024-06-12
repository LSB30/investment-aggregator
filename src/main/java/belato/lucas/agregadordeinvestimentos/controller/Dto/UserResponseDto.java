package belato.lucas.agregadordeinvestimentos.controller.Dto;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Stream;

public record UserResponseDto(String userName, String email, String password, Instant creationTimestamp, Instant updateTimestamp, Stream<java.lang.String>  accountId, Stream<java.lang.String> description) {
}
