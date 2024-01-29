package com.example.posts.Services;

import com.example.posts.entites.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IPosts {

         List<Post> getAllPosts();

        Post getPostById(int id);

         Post AddPost(int userId, String description, Date date_post , MultipartFile Picture) throws IOException ;

        Post updatePost(Post p);

        void deletePost(int id);
        Post likePost(int postId, int userId);
        Post dislikePost(int postId, int userId);




}
