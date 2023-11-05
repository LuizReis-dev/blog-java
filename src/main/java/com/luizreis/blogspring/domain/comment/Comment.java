package com.luizreis.blogspring.domain.comment;

import com.luizreis.blogspring.domain.post.Post;
import com.luizreis.blogspring.domain.user.User;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @Column(columnDefinition = "TEXT")
    private String text;
    private Instant postedAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {
    }

    public Comment(Long id, User author, String text, Instant postedAt, Post post) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.postedAt = postedAt;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Instant postedAt) {
        this.postedAt = postedAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
