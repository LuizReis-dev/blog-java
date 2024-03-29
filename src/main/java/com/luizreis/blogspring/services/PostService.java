package com.luizreis.blogspring.services;

import com.luizreis.blogspring.domain.comment.Comment;
import com.luizreis.blogspring.domain.like.Like;
import com.luizreis.blogspring.domain.post.Post;
import com.luizreis.blogspring.domain.user.User;
import com.luizreis.blogspring.dtos.comment.CommentDTO;
import com.luizreis.blogspring.dtos.like.LikeDTO;
import com.luizreis.blogspring.dtos.post.PostDTO;
import com.luizreis.blogspring.dtos.post.PostMinDTO;
import com.luizreis.blogspring.repositories.CommentRepository;
import com.luizreis.blogspring.repositories.LikeRepository;
import com.luizreis.blogspring.repositories.PostRepository;
import com.luizreis.blogspring.services.exceptions.ForbiddenException;
import com.luizreis.blogspring.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

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

    @Transactional
    public void delete(Long id) {

        User loggedUser = userService.getAuthenticatedUser();

        Post post = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found!"));

        if(post.getAuthor().getId() != loggedUser.getId()) {
            throw new ForbiddenException("Permission denied!");
        }

        repository.deleteById(id);
    }

    public List<PostMinDTO> getLoggedUserTimeline(){
        User loggedUser = userService.getAuthenticatedUser();
        List<PostMinDTO> posts = repository.selectUserTimelinePosts(loggedUser.getId())
                .stream().map(projection -> new PostMinDTO(projection)).toList();

        return posts;
    }

    public List<PostMinDTO> getPostsByUser(Long userId){
        repository.findById(userId).orElseThrow(() -> { throw new ResourceNotFoundException("User not found!");});

        List<PostMinDTO> posts = repository.selectPostsByUser(userId)
                .stream().map(projection -> new PostMinDTO(projection)).toList();

        return posts;
    }
}
