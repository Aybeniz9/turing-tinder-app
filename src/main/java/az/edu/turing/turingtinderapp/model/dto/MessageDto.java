package az.edu.turing.turingtinderapp.model.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MessageDto(
        Long id,
        Long senderId,
        Long receiverId,
        String content,
        LocalDate date
) {
}
