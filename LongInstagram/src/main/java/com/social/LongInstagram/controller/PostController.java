package com.social.LongInstagram.controller;


import com.social.LongInstagram.entities.Post;
import com.social.LongInstagram.entities.User;
import com.social.LongInstagram.exception.PostException;
import com.social.LongInstagram.exception.UserException;
import com.social.LongInstagram.service.PostService;
import com.social.LongInstagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/createPost")
    public Post createPost (@RequestBody Post post  , @RequestHeader("Authorization") String token) throws UserException , PostException {
        User user = userService.findUserProfile(token);
        Post newPost = postService.createdPost(post, user.getId());
        return newPost;
    }

    @GetMapping("/getPost")
    public ResponseEntity<Post> getPost( @RequestParam Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException{
        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost( @RequestParam Integer postId,String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);
        boolean isDeleted = postService.deletePost(user.getId(), postId);
        if (!isDeleted) {
            throw new PostException("Post deleted unsuccessfully");
        }
        return new ResponseEntity<>("Post Deleted Successfully", HttpStatus.OK);  // Trả về mã 200 OK và thông báo thành công
    }

    @GetMapping("/likePost")
    public ResponseEntity<Post> likePost( @RequestParam Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException{
        User user = userService.findUserProfile(token);
        Post likedPost = postService.likePost(postId, user.getId());
        return new ResponseEntity<>(likedPost,HttpStatus.OK);
    }

    @GetMapping("/unlikePost")
    public ResponseEntity<Post> unlikePost( @RequestParam Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException{
        User user = userService.findUserProfile(token);
        Post likedPost = postService.unlikePost(postId, user.getId());
        return new ResponseEntity<>(likedPost,HttpStatus.OK);
    }

    @GetMapping("/savedPost")
    public ResponseEntity<String> savedPost( @RequestParam Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException{
        User user = userService.findUserProfile(token);
        String savedPost = postService.savedPost(postId, user.getId());
        return new ResponseEntity<>(savedPost,HttpStatus.OK);
    }

    @GetMapping("/unsavedPost")
    public ResponseEntity<String> unsavedPost( @RequestParam Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException{
        User user = userService.findUserProfile(token);
        String unsavedPost = postService.unsavedPost(postId, user.getId());
        return new ResponseEntity<>(unsavedPost,HttpStatus.OK);
    }

}
