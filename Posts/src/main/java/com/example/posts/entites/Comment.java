package com.example.posts.entites;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_comment;

    private String message ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }
}