package com.luizreis.blogspring.services;

import com.luizreis.blogspring.domain.post.Post;
import com.luizreis.blogspring.domain.user.User;
import com.luizreis.blogspring.dtos.post.PostDTO;
import com.luizreis.blogspring.repositories.PostRepository;
import com.luizreis.blogspring.services.exceptions.ForbiddenException;
import com.luizreis.blogspring.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostServiceTests {

    private PostService postService;
    private PostRepository postRepository;
    private UserService userService;
    private User loggedUser;
    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        userService = mock(UserService.class);
        postService = new PostService(postRepository, userService);

        loggedUser = new User();
        loggedUser.setId(1L);
        loggedUser.setUsername("Test");
        when(userService.getAuthenticatedUser()).thenReturn(loggedUser);
    }

    @Test
    void insertShouldPersistPost(){
        PostDTO request = new PostDTO(null, "New Post", "");

        when(postRepository.save(any(Post.class))).thenAnswer(invocation -> {
            Post savedPost = invocation.getArgument(0);
            savedPost.setId(123L);
            return savedPost;
        });

        PostDTO result = postService.insert(request);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getAuthor(), loggedUser.getUsername());
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void deleteShouldDeleteObjectWhenPostExistsAndUserHasPermission(){
        Long existingId = 1L;
        Post post = new Post();
        post.setId(existingId);
        post.setAuthor(loggedUser);

        when(postRepository.findById(existingId)).thenReturn(java.util.Optional.of(post));
        doNothing().when(postRepository).deleteById(existingId);

        assertDoesNotThrow(() -> postService.delete(existingId));

        verify(postRepository, times(1)).deleteById(existingId);
    }

    @Test
    void deleteShouldThrowResourceNotFoundExceptionWhenIdNotExists(){
        Long nonExistingId = 2L;

        when(postRepository.findById(nonExistingId)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> postService.delete(nonExistingId));
        verify(postRepository, times(0)).deleteById(nonExistingId);
    }

    @Test
    void deleteShouldThrowForbiddenExceptionWhenUserHasNoPermission(){
        User otherUser = new User();
        otherUser.setId(2L);

        Long existingId = 1L;
        Post post = new Post();
        post.setId(existingId);
        post.setAuthor(otherUser);

        when(postRepository.findById(existingId)).thenReturn(java.util.Optional.of(post));

        assertThrows(ForbiddenException.class, () -> postService.delete(existingId));
        verify(postRepository, times(0)).deleteById(existingId);

    }
}
