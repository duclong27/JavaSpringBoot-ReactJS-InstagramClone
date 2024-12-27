package com.social.LongInstagram.dto;


import java.util.Objects;

public class Userdto {

    private Integer id;
    private String username;
    private String email;
    private String name;
    private String userImage;

    // Constructors
    public Userdto() {}

    public Userdto(Integer id, String username, String email, String name, String userImage) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.username =userImage;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail(), getName(), getUserImage());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj ==null)
            return  false;
        if(getClass()!=obj.getClass())
            return false;
        Userdto other = (Userdto)  obj;
        return Objects.equals(getId(), other.getId()) && Objects.equals(getUsername(), other.getUsername()) && Objects.equals(getEmail(), other.getEmail()) && Objects.equals(getName(), other.getName()) && Objects.equals(getUserImage(), other.getUserImage());
    }


}
