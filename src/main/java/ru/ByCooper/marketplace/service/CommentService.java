package ru.ByCooper.marketplace.service;

import ru.ByCooper.marketplace.entity.Comment;

public interface CommentService {

    Comment saveComment(Comment comment);

    Comment getComment(long id);

    void removeComment(Comment comment);
}