package com.social.LongInstagram.service;


import com.social.LongInstagram.entities.Comment;
import com.social.LongInstagram.exception.CommentException;
import com.social.LongInstagram.exception.PostException;
import com.social.LongInstagram.exception.UserException;



public interface CommentService {
    public Comment createComment(Comment comment , Integer postId, Integer userId) throws UserException , PostException;
    public Comment findCommentById(Integer commentId) throws CommentException;
    public Comment likeComment(Integer commentId, Integer userId) throws CommentException, UserException;
    public Comment unlikeComment(Integer commentId, Integer userId) throws CommentException, UserException;
    public boolean deleteComment(Integer commentId , Integer userId,Integer postId) throws CommentException , UserException;

}
