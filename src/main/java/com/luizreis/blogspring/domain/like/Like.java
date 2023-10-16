package com.luizreis.blogspring.domain.like;

import com.luizreis.blogspring.domain.post.Post;
import com.luizreis.blogspring.domain.user.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "tb_likes")
public class Like {

    @EmbeddedId
    private LikePK id;
    private Instant likedAt;

    public Like() {
    }

    public Like(User user, Post post, Instant likedAt) {
        this.id.setUser(user);
        this.id.setPost(post);
        this.likedAt = likedAt;
    }

    public User getUser(){
        return id.getUser();
    }

    public void setUser(User user){
        id.setUser(user);
    }

    public Post getPost(){
        return id.getPost();
    }

    public void setPost(Post post){
        id.setPost(post);
    }
    public Instant getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(Instant likedAt) {
        this.likedAt = likedAt;
    }
}
