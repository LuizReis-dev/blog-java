package com.luizreis.blogspring.services;

import com.luizreis.blogspring.domain.like.Like;
import com.luizreis.blogspring.domain.post.Post;
import com.luizreis.blogspring.domain.user.User;
import com.luizreis.blogspring.dtos.like.LikeDTO;
import com.luizreis.blogspring.repositories.LikeRepository;
import com.luizreis.blogspring.repositories.PostRepository;
import com.luizreis.blogspring.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class LikeService {

    private final LikeRepository repository;
    private final PostRepository postRepository;
    private final UserService userService;

    public LikeService(LikeRepository repository, PostRepository postRepository, UserService userService) {
        this.repository = repository;
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Transactional
    public LikeDTO create(Long postId){
        User loggedUser = userService.getAuthenticatedUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found!"));

        Like like = new Like(loggedUser, post, Instant.now());

        return new LikeDTO(repository.save(like));
    }
}
