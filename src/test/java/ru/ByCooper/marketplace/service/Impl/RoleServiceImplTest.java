package ru.ByCooper.marketplace.service.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ByCooper.marketplace.entity.Role;
import ru.ByCooper.marketplace.entity.User;
import ru.ByCooper.marketplace.repository.RoleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;
    private User user;
    private Role role;
    private static String userName = "test@mail.ru";
    public Role getSelectRole() {
        return new Role(1l, "ROLE_USER");
    }

    @Test
    void getRole() {
        Mockito.when(roleRepository.findByName(userName)).thenReturn(Optional.ofNullable(getSelectRole()));
        String expected = "ROLE_USER";
        Role actual = roleRepository.findByName(userName).get();
        verify(roleRepository, times(1)).findByName(userName);
        assertEquals(expected, actual.getName());
    }
}