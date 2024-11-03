package old.user.application.service;

import old.user.domain.aggregate.User;
import old.user.domain.vo.UserEmail;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAuthenticationService implements UserDetailsService {

    private final UserApplicationService userApplicationService;

    public UserAuthenticationService(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userApplicationService.getUserByEmail(new UserEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        Set<SimpleGrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail().value(),
                user.getPassword().value(),
                grantedAuthorities
        );
    }
}
