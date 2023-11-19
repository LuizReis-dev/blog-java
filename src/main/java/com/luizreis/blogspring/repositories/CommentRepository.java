package com.luizreis.blogspring.repositories;

import com.luizreis.blogspring.domain.comment.Comment;
import com.luizreis.blogspring.projections.CommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    @Query(nativeQuery = true, value =
                "SELECT" +
                "   tb_comments.id," +
                "   tb_comments.posted_at as postedAt," +
                "   tb_comments.text," +
                "   tb_comments.author_id as authorId," +
                "   tb_comments.post_id as postId," +
                "   tb_users.username as author " +
                "FROM " +
                "   tb_comments " +
                "INNER JOIN " +
                "   tb_users ON tb_users.id = tb_comments.author_id " +
                "WHERE " +
                "   post_id = :postId")
    List<CommentProjection> selectCommentByPostId(Long postId);
}
