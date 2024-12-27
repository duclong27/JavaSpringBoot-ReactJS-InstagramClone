package com.social.LongInstagram.controller;


import com.social.LongInstagram.entities.Comment;

import com.social.LongInstagram.entities.User;
import com.social.LongInstagram.exception.CommentException;
import com.social.LongInstagram.exception.PostException;
import com.social.LongInstagram.exception.UserException;
import com.social.LongInstagram.service.CommentService;
import com.social.LongInstagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/comment")
public class CommentController {
   @Autowired
   private CommentService commentService;
   @Autowired
   private UserService userService;

    @PostMapping("/create/{postId}")
    public Comment createdComment(@RequestBody Comment comment , @PathVariable Integer postId , @RequestHeader("Authorization") String token) throws UserException, PostException {
          User user = userService.findUserProfile(token);
          Comment createdComment = commentService.createComment(comment,postId,user.getId());
          return createdComment;
    }

    @GetMapping("/deleteComment/{commentId}")
    public Boolean deleteComment(@RequestBody Integer commentId , @PathVariable Integer postId, @RequestHeader("Authorization") String token ) throws CommentException,UserException{
           User user = userService.findUserProfile(token);
           Boolean deleteComment = commentService.deleteComment(commentId ,postId,user.getId());
           return true;
    }

    @GetMapping("/likeComment/{commentId}")
    public Comment likeComment(@RequestBody Integer commentId , @PathVariable Integer postId, @RequestHeader("Authorization") String token ) throws CommentException,UserException{
        User user = userService.findUserProfile(token);
        Comment comment = commentService.likeComment(commentId, user.getId());
        return comment;
    }

    @GetMapping("/unlikeComment/{commentId}")
    public Comment unlikeComment(@RequestBody Integer commentId , @PathVariable Integer postId, @RequestHeader("Authorization") String token ) throws CommentException,UserException{
        User user = userService.findUserProfile(token);
        Comment comment = commentService.unlikeComment(commentId,postId);
        return comment;
    }

    @GetMapping("/getComment/{comment Id}")
    public Comment getComment(@RequestBody Integer commentId , @PathVariable Integer postId, @RequestHeader("Authorization") String token ) throws CommentException,UserException{
        User user = userService.findUserProfile(token);
        Comment comment = commentService.findCommentById(commentId);
        return comment;
    }

}
