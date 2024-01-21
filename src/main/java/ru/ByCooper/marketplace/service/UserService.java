package ru.ByCooper.marketplace.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.ByCooper.marketplace.dto.AdsDTO;
import ru.ByCooper.marketplace.dto.ImageDTO;
import ru.ByCooper.marketplace.dto.PasswordUpdaterDTO;
import ru.ByCooper.marketplace.dto.UserUpdaterDTO;
import ru.ByCooper.marketplace.entity.User;

public interface UserService {

    User getUser(String username);

    void saveUser(User user);

    void setPassword(PasswordUpdaterDTO passwordUpdaterDTO, Authentication authentication);

    void updateInfo(UserUpdaterDTO userUpdaterDTO, Authentication authentication);

    ImageDTO getAvatar(Authentication authentication);

    ImageDTO updateAvatar(MultipartFile file, Authentication authentication);

    AdsDTO getUserAds(Authentication authentication);
}