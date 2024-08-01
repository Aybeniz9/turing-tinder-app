package az.edu.turing.turingtinderapp.model.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record UserDto(
        Long id,
        String name,
        String password,
        String photoUrl,
        LocalDate lastLogin,
        Set<Long> likedUserIds
) {
}

