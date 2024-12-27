package com.social.LongInstagram.serviceImpl;


import com.social.LongInstagram.dto.Userdto;
import com.social.LongInstagram.entities.Post;
import com.social.LongInstagram.entities.User;
import com.social.LongInstagram.exception.PostException;
import com.social.LongInstagram.exception.UserException;
import com.social.LongInstagram.repository.PostRepository;
import com.social.LongInstagram.repository.UserRepository;
import com.social.LongInstagram.service.PostService;
import com.social.LongInstagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post createdPost(Post post ,Integer userId) throws PostException, UserException {
        User user = userService.findUserById(userId);
        if(user == null){
            throw new UserException("No user found with this Id");
        }
        Userdto userdto = new Userdto();
        userdto.setId(user.getId());
        userdto.setEmail(user.getEmail());
        userdto.setName(user.getName());
        userdto.setUserImage(user.getImage());
        userdto.setUsername(user.getUsername());

        post.setUser(userdto);
        return postRepository.save(post);
    }
    @Override
    public Post findPostById(Integer postId) throws PostException {
        Optional<Post> getPostById = postRepository.findById(postId);
        if(getPostById.isPresent()){
            getPostById.get();
        }
         throw  new PostException("No post found with this id ");
    }
    @Override
    public boolean deletePost(Integer userId, Integer postId) throws PostException, UserException {
        User user = userService.findUserById(userId);
        Post post = findPostById(postId);
        if (user == null || user.getId() == null) {
            throw new UserException("No user found with this id");
        }
        if (post == null || post.getId() == null) {
            throw new PostException("No post found with this id");
        }
        postRepository.delete(post);  // Xóa bài đăng
        return true;
    }

    @Override
    public List<Post> getAllPostsByUserId(Integer userId) throws UserException {
        return null;
    }
    @Override
    public Post likePost(Integer userId, Integer postId) throws PostException ,UserException {
        User user = userService.findUserById(userId);
        Post post = findPostById(postId);
        if(user == null ){
            throw new UserException("No user found with this id");
        }
        if (post ==null ){
            throw new PostException("No post found with this id");
        }
        Userdto userdto = new Userdto();
        userdto.setId(user.getId());
        userdto.setUsername(user.getUsername());
        userdto.setEmail(user.getEmail());
        userdto.setUserImage(user.getImage());
        userdto.setName(user.getName());

        post.getLikesByUsers().add(userdto);
        throw new PostException("Comment liked unsuccessfully");
    }
    @Override
    public Post unlikePost(Integer userId, Integer postId) throws PostException ,UserException {
        User user = userService.findUserById(userId);
        Post post = findPostById(postId);
        if(user == null){
            throw new UserException("No user found with this id");
        }
        if (post == null){
            throw new PostException("No post found with this id");
        }
        Userdto userdto = new Userdto();
        userdto.setId(user.getId());
        userdto.setUsername(user.getUsername());
        userdto.setEmail(user.getEmail());
        userdto.setUserImage(user.getImage());
        userdto.setName(user.getName());
        post.getLikesByUsers().remove(userdto);
        throw new PostException("Comment unliked unsuccessfully");
    }
    @Override
    public String savedPost(Integer userId, Integer postId) throws PostException, UserException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if(!user.getSavedPosts().contains(post))
        {
            user.getSavedPosts().add(post);
        }
        return "Post was saved successfully";
    }

    @Override
    public String unsavedPost(Integer userId, Integer postId) throws PostException, UserException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if(user.getSavedPosts().contains(post))
        {
            user.getSavedPosts().remove(post);
        }
        return "Post was unsaved successfully";
    }
}
