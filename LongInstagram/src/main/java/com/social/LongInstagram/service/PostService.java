package com.social.LongInstagram.service;


import com.social.LongInstagram.entities.Post;
import com.social.LongInstagram.entities.User;
import com.social.LongInstagram.exception.PostException;
import com.social.LongInstagram.exception.UserException;

import java.util.List;

public interface PostService {
    public Post createdPost(Post post ,Integer userId) throws PostException, UserException;
    public Post findPostById(Integer postId) throws PostException;
    public boolean deletePost(Integer userId , Integer postId) throws PostException , UserException;
    public List<Post> getAllPostsByUserId(Integer userId) throws UserException ,PostException;
    public Post likePost(Integer userId , Integer postId) throws PostException ,UserException;
    public Post unlikePost(Integer userId , Integer postId) throws PostException , UserException;
    public String savedPost(Integer userId , Integer postId) throws PostException, UserException;
    public String unsavedPost(Integer userId , Integer postId) throws PostException, UserException;
}
