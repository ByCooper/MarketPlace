package ru.ByCooper.marketplace.service.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.misusing.PotentialStubbingProblem;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ByCooper.marketplace.dto.other.Credentials;
import ru.ByCooper.marketplace.entity.Role;
import ru.ByCooper.marketplace.entity.User;
import ru.ByCooper.marketplace.repository.UserRepository;
import ru.ByCooper.marketplace.security.SecurityUserService;
import ru.ByCooper.marketplace.utils.exception.EntityNotFound;
import ru.ByCooper.marketplace.utils.exception.NotAuthorizedException;
import ru.ByCooper.marketplace.utils.exception.UserAlreadyExistsException;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private SecurityUserService userService;
    @Mock
    private UserDetails userDetails;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void loginFailed() {
        Role role1 = new Role(1L, "ROLE_USER");
        Role role2 = new Role(2L, "ADMIN");
        Collection<Role> roles = List.of(role1, role2);
        Collection<? extends GrantedAuthority> authorities = authorities(roles);

        lenient().when(userRepository.findByUsername("John")).thenReturn(Optional.empty());
        lenient().when(userService.getWrappedUser(any())).thenReturn(Optional.empty());
        lenient().when(userService.loadUserByUsername(any())).thenReturn(new org.springframework.security.core.userdetails.User("John", "John", authorities));
        lenient().when(userDetails.getPassword()).thenReturn("John");
        lenient().when(encoder.matches(anyString(), anyString())).thenReturn(true);

        assertThrows(EntityNotFound.class,
                () -> authService.login(new Credentials("John2", "John", "John", "John", "John", "John")));
    }

    @Test
    void register() {
        lenient().when(userRepository.findByUsername("John")).thenReturn(Optional.of(new User()));
        lenient().when(userService.getWrappedUser(any())).thenReturn(Optional.of(new User()));

        assertThrows(UserAlreadyExistsException.class,
                () -> authService.register(new Credentials("John", "John", "John", "John", "John", "John")));
    }

    private Collection<? extends GrantedAuthority> authorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}


