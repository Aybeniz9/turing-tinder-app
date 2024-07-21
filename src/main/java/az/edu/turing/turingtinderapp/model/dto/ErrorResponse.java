package az.edu.turing.turingtinderapp.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
public record ErrorResponse(
        Integer errorCode,
        String errorMessage
) {
}
