package old.user.infrastructure.dto;

import old.user.domain.aggregate.Authority;
import com.chatapp.user.infrastructure.dto.AuthorityDtoBuilder;
import org.jilt.Builder;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record AuthorityDto(String name) {

    public static Set<AuthorityDto> fromSet(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> AuthorityDtoBuilder.authorityDto().name(authority.getName().name()).build())
                .collect(Collectors.toSet());
    }
}
