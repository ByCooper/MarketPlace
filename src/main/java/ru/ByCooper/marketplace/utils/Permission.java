package ru.ByCooper.marketplace.utils;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ru.ByCooper.marketplace.entity.User;
import ru.ByCooper.marketplace.utils.exception.NotAuthorizedException;
import ru.ByCooper.marketplace.constants.RoleType;

import java.util.Collection;

public class Permission {

    private static boolean isAdmin(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = false;
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(RoleType.ADMIN.getRole())) {
                isAdmin = true;
                break;
            }
        }
        return isAdmin;
    }

    private static boolean isOwner(User user, Authentication authentication) {
        return user.getUsername().equals(authentication.getName());
    }

    public static void checkPermission(User user, Authentication authentication) {
        boolean isOwner = isOwner(user, authentication);
        boolean isAdmin = isAdmin(authentication);
        if (!(isOwner || isAdmin)) {
            throw new NotAuthorizedException(HttpStatus.FORBIDDEN, "У пользователя нет разрешения!");
        }
    }
}