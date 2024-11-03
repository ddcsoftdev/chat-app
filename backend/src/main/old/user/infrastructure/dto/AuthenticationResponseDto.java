package old.user.infrastructure.dto;

import com.chatapp.user.infrastructure.dto.AuthenticationResponseDtoBuilder;
import org.jilt.Builder;

@Builder
public record AuthenticationResponseDto(String jwtToken) {

    public static AuthenticationResponseDto from(String jwtToken) {
        return AuthenticationResponseDtoBuilder
                .authenticationResponseDto()
                .jwtToken(jwtToken)
                .build();
    }
}
