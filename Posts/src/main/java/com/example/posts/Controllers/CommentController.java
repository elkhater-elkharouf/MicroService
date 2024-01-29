package com.example.posts.Controllers;

import com.example.posts.Services.IComment;
import com.example.posts.entites.Comment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class CommentController {
    IComment icomment;

    @GetMapping("admin/getCommentById/{id}")
    public Comment getCommentById(@PathVariable("id") int id) {
        return icomment.getCommentById(id);
    }

    @PostMapping("user/addCommentToPost/{postId}")
    public Comment addCommentToPost(@RequestBody Comment comment, @PathVariable("postId") int postId) {
        return icomment.addCommentToPost(comment, postId);
    }

    @PutMapping("admin/updateComment")
    public Comment updateComment(@RequestBody Comment comment) {
        return icomment.updateComment(comment);
    }

    @DeleteMapping("admin/deleteComment/{id}")
    public void deleteComment(@PathVariable("id") int id) {
        icomment.deleteComment(id);
    }
}
