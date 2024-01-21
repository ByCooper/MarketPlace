package ru.ByCooper.marketplace.service;

import ru.ByCooper.marketplace.dto.other.Credentials;

public interface AuthService {

    void login(Credentials credentials);

    void register(Credentials credentials);
}