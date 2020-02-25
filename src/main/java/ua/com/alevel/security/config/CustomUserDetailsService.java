package ua.com.alevel.security.config;

import lombok.AllArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.security.persistence.entity.Role;
import ua.com.alevel.security.persistence.entity.User;
import ua.com.alevel.security.persistence.entity.UserToken;
import ua.com.alevel.security.persistence.repository.UserRepository;
import ua.com.alevel.security.persistence.repository.UserTokenRepository;

import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));


        UserToken userToken = userTokenRepository.findByUserId(user.getId());
        if (userToken == null) {
            userToken = new UserToken();
        }
        userToken.setToken(generateToken());
        userToken.setUserId(user.getId());
        userTokenRepository.save(userToken);
        return new CustomUserDetails(user, getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map(Role::getName).toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(userRoles);
    }

    private String generateToken() {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        UserToken userToken = userTokenRepository.findByToken(token);
        if (userToken != null) {
            return generateToken();
        }
        return token;
    }
}
