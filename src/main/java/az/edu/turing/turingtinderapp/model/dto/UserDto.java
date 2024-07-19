package az.edu.turing.turingtinderapp.model.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserDto(
        Long id,
        String name,
        String password,
        String photoUrl,
        LocalDate lastLogin
) {
}
