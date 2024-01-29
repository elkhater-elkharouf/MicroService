package com.example.posts.Services;

import com.example.posts.entites.Comment;

public interface IComment {

        public Comment getCommentById(int id);

        public Comment addCommentToPost(Comment c,int id);

        public Comment updateComment(Comment c);

        public void deleteComment(int id);

}
