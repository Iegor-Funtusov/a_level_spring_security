package ua.com.alevel.security.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.alevel.security.config.CustomUserDetails;

@UtilityClass
public class SecurityUtil {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Integer getUserId() {
        return ((CustomUserDetails) SecurityUtil.getAuthentication().getPrincipal()).getId();
    }
}
