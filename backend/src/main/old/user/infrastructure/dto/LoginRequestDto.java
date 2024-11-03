package old.user.infrastructure.dto;

import com.chatapp.user.infrastructure.dto.LoginRequestDtoBuilder;
import org.jilt.Builder;

@Builder
public record LoginRequestDto(String username, String password) {

    public static LoginRequestDto from(String username, String password) {
        return LoginRequestDtoBuilder
                .loginRequestDto()
                .username(username)
                .password(password)
                .build();
    }
}
