package com.luizreis.blogspring.services;

import com.luizreis.blogspring.domain.comment.Comment;
import com.luizreis.blogspring.domain.post.Post;
import com.luizreis.blogspring.domain.user.User;
import com.luizreis.blogspring.dtos.comment.CommentDTO;
import com.luizreis.blogspring.repositories.CommentRepository;
import com.luizreis.blogspring.repositories.PostRepository;
import com.luizreis.blogspring.services.exceptions.ForbiddenException;
import com.luizreis.blogspring.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class CommentService {

    private final UserService userService;
    private final CommentRepository repository;
    private final PostRepository postRepository;

    public CommentService(UserService userService, CommentRepository repository, PostRepository postRepository) {
        this.userService = userService;
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @Transactional
    public CommentDTO create(Long postId, CommentDTO dto) {
        User loggedUser = userService.getAuthenticatedUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found!"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setAuthor(loggedUser);
        comment.setPostedAt(Instant.now());
        comment.setText(dto.getText());

        return new CommentDTO(repository.save(comment));
    }

    @Transactional
    public void delete(Long commentId){
        User loggedUser = userService.getAuthenticatedUser();

        Comment comment = repository.findById(commentId)
                 .orElseThrow(() -> new ResourceNotFoundException("Comment not found!"));

        if((loggedUser.getId() != comment.getAuthor().getId()) && (loggedUser.getId() != comment.getPost().getAuthor().getId()))
            throw new ForbiddenException("Permission denied!");

        repository.delete(comment);
    }
}
