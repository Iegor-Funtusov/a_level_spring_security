package ua.com.alevel.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUserDetails extends User {

    private static final long serialVersionUID = 6936974415584868465L;
    private Integer id;

    public CustomUserDetails(ua.com.alevel.security.persistence.entity.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
        this.id = user.getId();
    }
}
