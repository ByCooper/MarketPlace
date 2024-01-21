package ru.ByCooper.marketplace.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.ByCooper.marketplace.constants.RoleType;
import ru.ByCooper.marketplace.repository.RoleRepository;
import ru.ByCooper.marketplace.utils.exception.EntityNotFound;
import ru.ByCooper.marketplace.entity.Role;
import ru.ByCooper.marketplace.service.RoleService;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRole(String name) {
        String preparedRole = RoleType.getPreparedRole(name);
        log.info("Запрос на получение роли!");
        return roleRepository.findByName(preparedRole).orElseThrow(()
                -> new EntityNotFound(Role.class, HttpStatus.BAD_REQUEST));
    }
}