package com.luizreis.blogspring.services;

import com.luizreis.blogspring.domain.like.Like;
import com.luizreis.blogspring.domain.post.Post;
import com.luizreis.blogspring.domain.user.User;
import com.luizreis.blogspring.dtos.like.LikeDTO;
import com.luizreis.blogspring.dtos.post.PostDTO;
import com.luizreis.blogspring.repositories.LikeRepository;
import com.luizreis.blogspring.repositories.PostRepository;
import com.luizreis.blogspring.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class PostService {
    private final PostRepository repository;
    private final UserService userService;
    private final LikeRepository likeRepository;

    public PostService(PostRepository repository, UserService userService, LikeRepository likeRepository) {
        this.repository = repository;
        this.userService = userService;
        this.likeRepository = likeRepository;
    }

    @Transactional
    public PostDTO insert(PostDTO request){
        User loggedUser = userService.getAuthenticatedUser();

        Post post = new Post();
        post.setAuthor(loggedUser);
        post.setText(request.getText());
        post.setPostedAt(Instant.now());

        return new PostDTO(repository.save(post));
    }

    @Transactional
    public LikeDTO like(Long postId){
        User loggedUser = userService.getAuthenticatedUser();

        Post post = repository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found!"));

        Like like = new Like(loggedUser, post, Instant.now());

        return new LikeDTO(likeRepository.save(like));
    }
}
