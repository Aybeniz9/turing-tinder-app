package az.edu.turing.turingtinderapp.model.dto;

import lombok.Builder;

@Builder
public record LikeDto(
        Long id,
        Long userId,
        Long likedUserId,
        Boolean reaction
) {
}
