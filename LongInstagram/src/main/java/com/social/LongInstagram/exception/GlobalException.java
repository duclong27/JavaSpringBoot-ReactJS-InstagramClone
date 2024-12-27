package com.social.LongInstagram.exception;


import com.social.LongInstagram.entities.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

public class GlobalException {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(UserException userException, WebRequest webRequest) {
        ErrorDetails err = new ErrorDetails(userException.getMessage(),webRequest.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ErrorDetails> postExceptionHandler(PostException postException, WebRequest webRequest) {
        ErrorDetails err = new ErrorDetails(postException.getMessage(),webRequest.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ErrorDetails> commentExceptionHandler(CommentException commentException, WebRequest webRequest) {
        ErrorDetails err = new ErrorDetails(commentException.getMessage(),webRequest.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> otherMethodExceptionHandler(UserException userException, WebRequest webRequest) {
        ErrorDetails err = new ErrorDetails(userException.getMessage(),webRequest.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> methodNotValidExceptionHandler(UserException userException, WebRequest webRequest) {
        ErrorDetails err = new ErrorDetails(userException.getMessage(),webRequest.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StoryException.class)
    public ResponseEntity<ErrorDetails> storyExceptionHandler(StoryException storyException, WebRequest webRequest) {
        ErrorDetails err = new ErrorDetails(storyException.getMessage(),webRequest.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }



}
