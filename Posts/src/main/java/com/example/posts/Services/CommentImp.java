package com.example.posts.Services;


import com.example.posts.entites.Comment;
import com.example.posts.entites.Post;
import com.example.posts.repositories.CommentRepository;
import com.example.posts.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentImp implements IComment{
   CommentRepository commentRepository ;
   PostRepository postRepository;
    @Override
    public Comment getCommentById(int id) {
        return commentRepository.findById(id).orElse(null);    }

    @Override
    public Comment addCommentToPost(Comment c, int postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            c.setPost(post);
            return commentRepository.save(c);
        }
        return null; // Gérez cette condition d'erreur en conséquence
    }

    @Override
    public Comment updateComment(Comment c) {
        if (commentRepository.existsById(c.getId_comment())) {
            // Rétablir l'association avec la publication avant la mise à jour
            Comment existingComment = commentRepository.getOne(c.getId_comment());
            c.setPost(existingComment.getPost());

            return commentRepository.save(c);
        }
        return null; // Gérez cette condition d'erreur en conséquence
    }
    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}
