package com.luizreis.blogspring.services;

import com.luizreis.blogspring.domain.post.Post;
import com.luizreis.blogspring.domain.user.User;
import com.luizreis.blogspring.dtos.post.PostDTO;
import com.luizreis.blogspring.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class PostService {
    private final PostRepository repository;
    private final UserService userService;

    public PostService(PostRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
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
}
