package com.luizreis.blogspring.services;

import com.luizreis.blogspring.domain.follow.UserFollow;
import com.luizreis.blogspring.domain.user.User;
import com.luizreis.blogspring.dtos.follow.FollowDTO;
import com.luizreis.blogspring.repositories.UserFollowRepository;
import com.luizreis.blogspring.repositories.UserRepository;
import com.luizreis.blogspring.services.exceptions.IllegalOperation;
import com.luizreis.blogspring.services.exceptions.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserFollowRepository userFollowRepository;

    public UserService(UserRepository repository, UserFollowRepository userFollowRepository) {
        this.repository = repository;
        this.userFollowRepository = userFollowRepository;
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = repository.findByUsername(userName)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return user;
    }

    @Transactional
    public FollowDTO follow(Long followedUserId){
        User followedUser = repository.findById(followedUserId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        User loggedUser = getAuthenticatedUser();
        if(loggedUser.getId() == followedUser.getId()) throw  new IllegalOperation("User can't follow him self");
        UserFollow userFollow = new UserFollow(loggedUser, followedUser, Instant.now());

        UserFollow savedUserFollow = userFollowRepository.save(userFollow);

        return new FollowDTO(savedUserFollow);
    }
}
