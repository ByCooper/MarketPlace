package ru.ByCooper.marketplace.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.ByCooper.marketplace.dto.*;
import ru.ByCooper.marketplace.entity.Ad;

public interface AdService {

    AdsDTO getAllAds();

    AdDTO addAd(AdUpdaterDTO adUpdaterDTO, MultipartFile file,
                Authentication authentication);

    Ad getAd(long id);

    void removeAd(long id, Authentication authentication);

    AdDTO updateAd(long id, AdUpdaterDTO adUpdaterDTO,
                   Authentication authentication);

    ImageDTO updateAdImage(long id, MultipartFile file,
                           Authentication authentication);

    ImageDTO getAdImage(long id);

    CommentsDTO getAdComments(long id);

    CommentDTO addCommentToAd(long id, CommentUpdaterDTO commentUpdaterDTO);

    void removeComment(long id, long commentId, Authentication authentication);

    CommentDTO updateComment(long id, long commentId, CommentUpdaterDTO commentUpdaterDTO,
                             Authentication authentication);

    void saveAd(Ad ad);

    void removeAd(Ad ad);
}