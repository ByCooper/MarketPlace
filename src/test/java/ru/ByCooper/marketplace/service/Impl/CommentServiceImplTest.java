package ru.ByCooper.marketplace.service.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ByCooper.marketplace.entity.Ad;
import ru.ByCooper.marketplace.entity.Comment;
import ru.ByCooper.marketplace.repository.CommentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    public Comment getCommentTest() {
        return new Comment(1L, 1705870962645L, "Ненадежный продавец", new Ad());
    }

    @Test
    void getComment() {
        Mockito.when(commentRepository.findById(getCommentTest().getId())).thenReturn(Optional.ofNullable(getCommentTest()));
        Comment actual = commentService.getComment(1L);
        String expected = getCommentTest().getText();
        assertEquals(expected, actual.getText());
        verify(commentRepository, times(1)).findById(getCommentTest().getId());
    }

    @Test
    void saveComment() {
        Mockito.when(commentRepository.save(any())).thenReturn(getCommentTest());
        Comment actual = commentService.saveComment(getCommentTest());
        String expected_text = "Ненадежный продавец";
        Long expected_id = 1L;
        Long expected_timeCreate = 1705870962645L;
        assertEquals(expected_id, actual.getId());
        assertEquals(expected_timeCreate, actual.getCreationTime());
        assertEquals(expected_text, actual.getText());
    }

}