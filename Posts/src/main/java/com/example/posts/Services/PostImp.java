package com.example.posts.Services;

import org.springframework.util.StringUtils;
import com.example.posts.entites.Post;
import com.example.posts.entites.User;
import com.example.posts.repositories.PostRepository;
import com.example.posts.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class PostImp implements IPosts {

    PostRepository postRepository;
    UserRepository userRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post AddPost(int userId, String description, Date date_post , MultipartFile Picture) throws IOException {
        Post p = new Post();
        User user = userRepository.findById(userId).orElse(null);
      String filename = StringUtils.cleanPath(Picture.getOriginalFilename());
        if(filename.contains("..")){
            System.out.println("!!! Not a valid File");
        }
        p.setUser(user);
        p.setDate_post(date_post);
        p.setDescription(description);
        p.setPicture(Base64.getEncoder().encodeToString(Picture.getBytes()));
        return postRepository.save(p);
    }


    @Override
    public Post updatePost(Post p) {
        return postRepository.save(p);
    }

    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }


    public Post likePost(int postId, int userId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            Map<Integer, Boolean> userLikes = post.getUserLikes();

            if (userLikes.containsKey(userId)) {
                if (userLikes.get(userId)) {
                    // Si l'utilisateur a déjà aimé, annulez son like
                    post.setLikes(post.getLikes() - 1);
                    userLikes.put(userId, false);
                } else {
                    // Sinon, ajoutez son like
                    post.setLikes(post.getLikes() + 1);
                    userLikes.put(userId, true);
                }
            } else {
                // Si l'utilisateur n'a pas déjà aimé, ajoutez son like
                post.setLikes(post.getLikes() + 1);
                userLikes.put(userId, true);
            }

            return postRepository.save(post);
        }
        return null; // Gérez cette condition d'erreur en conséquence

    }


    public Post dislikePost(int postId, int userId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            Map<Integer, Boolean> userDislikes = post.getUserDislikes();

            if (userDislikes.containsKey(userId)) {
                if (userDislikes.get(userId)) {
                    // Si l'utilisateur a déjà aimé, annulez son Dislike
                    post.setDislikes(post.getDislikes() - 1);
                    userDislikes.put(userId, false);
                } else {
                    // Sinon, ajoutez son Dislike
                    post.setDislikes(post.getDislikes() + 1);
                    userDislikes.put(userId, true);
                }
            } else {
                // Si l'utilisateur n'a pas déjà aimé, ajoutez son Dislike
                post.setDislikes(post.getDislikes() + 1);
                userDislikes.put(userId, true);
            }

            return postRepository.save(post);
        }
        return null; // Gérez cette condition d'erreur en conséquence

    }

}