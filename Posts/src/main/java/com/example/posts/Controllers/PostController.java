package com.example.posts.Controllers;

import com.example.posts.Services.IPosts;
import com.example.posts.entites.Post;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class PostController {
    IPosts iPost;

    @GetMapping("admin/getPostById/{id}")
    public Post getPostById(@PathVariable("id")int id){
        return iPost.getPostById(id);
    }

    @GetMapping("user/getAllpost")
    public List<Post> getAllPost(){
        return iPost.getAllPosts();
    }

    @PostMapping(path = "user/addPost/{idUser}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Post> createPost(@PathVariable int idUser, @RequestParam String description, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")  Date date_post, @RequestParam MultipartFile Picture) throws IOException {
        return new ResponseEntity<>(iPost.AddPost(idUser,description,date_post,Picture), HttpStatus.OK);
    }


    @PutMapping("admin/updatePost")
    public Post updatePost(@RequestBody Post p){
        return iPost.updatePost(p);
    }

    @DeleteMapping("admin/deletePost/{id}")
    public void deletePost(@PathVariable("id")int id){
        iPost.deletePost(id);
    }


    @PutMapping("user/likePost/{postId}/{userId}")
    public Post likePost(@PathVariable int postId, @PathVariable int userId) {
        return iPost.likePost(postId, userId);
    }

        @PutMapping("user/dislikePost/{postId}/{userId}")
    public Post dislikePost(@PathVariable int postId, @PathVariable int userId) {
        return iPost.dislikePost(postId, userId);
    }

}
