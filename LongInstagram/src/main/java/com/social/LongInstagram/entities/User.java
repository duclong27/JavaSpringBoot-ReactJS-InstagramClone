package com.social.LongInstagram.entities;

import com.social.LongInstagram.dto.Userdto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Getter
@Setter
@Builder
@Table(name="user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="email")
    private String email;
    @Column(name="username")
    private String username;
    @Column(name="name")
    private String name;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="password")
    private String password;
    @Column(name="mobile")
    private String mobile;
    @Column(name="website")
    private String website;
    @Column(name="gender")
    private String gender;

    @Column(name="image")
    private String image;

    @Embedded
    @ElementCollection
    private Set<Userdto> followers = new HashSet<Userdto>();
    @Embedded
    @ElementCollection
    private Set<Userdto> following = new HashSet<Userdto>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Story> stories = new ArrayList<>();
    @ManyToMany
    private List<Post> savedPosts = new ArrayList<>();

    public User() {
    }
    public User(Integer id, String email, String username, String name, String phoneNumber, String password, String mobile, String website, String gender, String image, Set<Userdto> followers, Set<Userdto> following, List<Story> stories, List<Post> savedPosts) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.mobile = mobile;
        this.website = website;
        this.gender = gender;
        this.image = image;
        this.followers = followers;
        this.following = following;
        this.stories = stories;
        this.savedPosts = savedPosts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Userdto> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Userdto> followers) {
        this.followers = followers;
    }

    public Set<Userdto> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Userdto> following) {
        this.following = following;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<Post> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List<Post> savedPosts) {
        this.savedPosts = savedPosts;
    }
}
