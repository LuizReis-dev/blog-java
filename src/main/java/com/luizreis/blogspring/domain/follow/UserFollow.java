package com.luizreis.blogspring.domain.follow;

import com.luizreis.blogspring.domain.user.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "tb_user_follow")
public class UserFollow {

    @EmbeddedId
    private UserFollowPK id = new UserFollowPK();
    private Instant followedAt;


    public UserFollow() {
    }

    public UserFollow(User user,User followedUser,Instant followedAt) {
        this.id.setUser(user);
        this.id.setFollowedUser(followedUser);
        this.followedAt = followedAt;
    }

    public User getUser(){
        return id.getUser();
    }

    public void setUser(User user){
        id.setUser(user);
    }

    public User getFollowedUser(){
        return id.getFollowedUser();
    }

    public void setFollowedUser(User followedUser){
        id.setFollowedUser(followedUser);
    }
    public Instant getFollowedAt() {
        return followedAt;
    }

    public void setFollowedAt(Instant followedAt) {
        this.followedAt = followedAt;
    }
}
