package ru.ByCooper.marketplace.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ByCooper.marketplace.dto.other.Credentials;
import ru.ByCooper.marketplace.entity.User;
import ru.ByCooper.marketplace.security.SecurityUserService;
import ru.ByCooper.marketplace.utils.Paths;
import ru.ByCooper.marketplace.utils.exception.EntityNotFound;
import ru.ByCooper.marketplace.utils.exception.UserAlreadyExistsException;
import ru.ByCooper.marketplace.utils.exception.WrongPasswordException;
import ru.ByCooper.marketplace.entity.Role;
import ru.ByCooper.marketplace.service.AuthService;

import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final SecurityUserService userService;
    private final PasswordEncoder encoder;
    private final RoleServiceImpl roleServiceImpl;

    @Autowired
    public AuthServiceImpl(SecurityUserService userService, PasswordEncoder encoder,
                           RoleServiceImpl roleServiceImpl) {
        this.userService = userService;
        this.encoder = encoder;
        this.roleServiceImpl = roleServiceImpl;
    }

    @Override
    public void login(Credentials credentials) {
        String username = credentials.username;
        Optional<User> optional = userService.getWrappedUser(username);
        if (optional.isEmpty()) {
            throw new EntityNotFound(User.class, HttpStatus.NOT_FOUND);
        }
        UserDetails userDetails = userService.loadUserByUsername(username);
        boolean matches = encoder.matches(credentials.password, userDetails.getPassword());
        if (!matches) {
            throw new WrongPasswordException(HttpStatus.UNAUTHORIZED, "Пароли не совпадают!");
        }
        log.info("Авторизация пользователя " + username);
    }

    @Override
    public void register(Credentials credentials) {
        Optional<User> optional = userService.getWrappedUser(credentials.username);
        if (optional.isPresent()) {
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST);
        }
        if (credentials.password == null) {
            throw new WrongPasswordException(HttpStatus.BAD_REQUEST, "Пароль отсутствует!");
        }
        createUser(credentials);
        log.info("Регистрация пользователя " + credentials.username);
    }

    private void createUser(Credentials credentials) {
        User user = new User();
        Role role = roleServiceImpl.getRole(credentials.role);
        user.setPassword(encoder.encode(credentials.password));
        user.setUsername(credentials.username);
        user.setFirstName(credentials.firstName);
        user.setLastName(credentials.lastName);
        user.setPhone(credentials.phone);
        user.getRoles().add(role);
        user.setEmail(credentials.username);
        user.setAvatarPath(Paths.STANDARD_AVATAR_PATH);
        userService.saveUser(user);
    }
}