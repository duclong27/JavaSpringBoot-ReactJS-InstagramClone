package com.social.LongInstagram.service;


import com.social.LongInstagram.dto.AuthenticationRequest;
import com.social.LongInstagram.entities.User;
import com.social.LongInstagram.exception.UserException;

import java.util.List;

public interface UserService {
    public User registerUser(User user) throws UserException;
    public User findUserById(Integer id) throws UserException;
    public User findUserProfile(String token) throws UserException;
    public User findUserByUsername(String username) throws UserException;
    public User findUserByEmail(String email) throws UserException;
    public String followUser(Integer reqUserId , Integer followerUserId) throws UserException;
    public String unfollowUser(Integer reqUserId , Integer followerUserId) throws UserException;
    public List<User> searchUser(String query) throws UserException;
    public List<User> findAllUserByIds(List<Integer>userIds) throws UserException;
    public User updateUserDetails(User updatedUser ,User existingUser);



}
