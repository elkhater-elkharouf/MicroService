package com.example.posts.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_post;

    private String description ;
    private int likes ;
    private int dislikes ;
    @Temporal(TemporalType.DATE)
    private Date date_post ;
    @Lob
    private String Picture ;


    @ElementCollection
    @MapKeyColumn(name="user_id")
    @Column(name="action")
    @CollectionTable(name="post_user_likes", joinColumns=@JoinColumn(name="post_id"))
    private Map<Integer, Boolean> userLikes = new HashMap<>(); // userId -> liked

    @ElementCollection
    @MapKeyColumn(name = "user_id")
    @Column(name = "dislikes")
    @CollectionTable(name = "userdislikepost", joinColumns = @JoinColumn(name = "postid"))
    private Map<Integer, Boolean> userDislikes = new HashMap<>(); // userId -> disliked


    {
        userLikes = new HashMap<>();
        userDislikes = new HashMap<>();

    }



    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
   // @JsonIgnore
    Set<Comment> comments;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

}
