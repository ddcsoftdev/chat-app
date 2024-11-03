package old.user.infrastructure.dto;

import org.jilt.Builder;

@Builder
public record RegisterRequestDto(
        String email,
        String password,
        String firstName,
        String lastName
) {
    // Optional validation method
    public boolean isValid() {
        return email != null &&
                password != null &&
                firstName != null  &&
                lastName != null;
    }
}

