package com.social.LongInstagram.entities;


import com.social.LongInstagram.dto.Userdto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image",nullable = false)
    private String image;

    @Column(name ="caption")
    private String caption;

    @Column(name = " created_at")
    private LocalDateTime createdAt;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "user_id")),
            @AttributeOverride(name = "email", column = @Column(name = "user_email"))
    })
    private Userdto user;

    @Embedded
    @ElementCollection
    @JoinTable(name ="likedByUsers", joinColumns = @JoinColumn(name="user_id"))
    Set<Userdto> likesByUsers = new HashSet<>();

    @OneToMany
    List<Comment> comments = new ArrayList<>();
}
