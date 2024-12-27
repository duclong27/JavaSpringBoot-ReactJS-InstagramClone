package com.social.LongInstagram.entities;


import com.social.LongInstagram.dto.Userdto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "user_id")),
            @AttributeOverride(name = "email", column = @Column(name = "user_email"))
    })
    private Userdto user;

    @Column(name="content")
    private String content;

    @Embedded
    @ElementCollection
    private Set<Userdto> likesByUsers= new HashSet<>();

    @Column(name="created_at")
    private LocalDateTime creatAt;

    public Comment(Integer id, Userdto user, String content, Set<Userdto> likesByUsers, LocalDateTime creatAt) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.likesByUsers = likesByUsers;
        this.creatAt = creatAt;
    }

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Userdto getUser() {
        return user;
    }

    public void setUser(Userdto user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Userdto> getLikesByUsers() {
        return likesByUsers;
    }

    public void setLikesByUsers(Set<Userdto> likesByUsers) {
        this.likesByUsers = likesByUsers;
    }

    public LocalDateTime getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }
}
