package com.social.LongInstagram.serviceImpl;



import com.social.LongInstagram.dto.Userdto;
import com.social.LongInstagram.entities.Post;
import com.social.LongInstagram.entities.User;
import com.social.LongInstagram.entities.Comment;
import com.social.LongInstagram.exception.CommentException;
import com.social.LongInstagram.exception.PostException;
import com.social.LongInstagram.exception.UserException;
import com.social.LongInstagram.repository.CommentRepository;
import com.social.LongInstagram.repository.PostRepository;
import com.social.LongInstagram.repository.UserRepository;
import com.social.LongInstagram.service.CommentService;
import com.social.LongInstagram.service.PostService;
import com.social.LongInstagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws UserException , PostException {
        // Tìm user và post từ cơ sở dữ liệu
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);
        // Kiểm tra user có tồn tại không
        if (user == null) {
            throw new UserException("User not found with ID: " + userId);
        }
        // Tạo Userdto từ thông tin user
        Userdto userdto = new Userdto();
        userdto.setId(user.getId());
        userdto.setUsername(user.getUsername());
        userdto.setName(user.getName());
        userdto.setEmail(user.getEmail());
        userdto.setUserImage(user.getImage());
        // Gán thông tin user vào comment
        comment.setUser(userdto);
        comment.setCreatAt(LocalDateTime.now());
        // Lưu comment vào cơ sở dữ liệu
        Comment createdComment = commentRepository.save(comment);
        // Thêm comment vào danh sách comments của post
        post.getComments().add(createdComment);
        postRepository.save(post);
        // Trả về comment đã tạo
        return  createdComment ;
    }
    @Override
    public Comment findCommentById(Integer commentId) throws CommentException {
        Optional<Comment>opt = commentRepository.findCommentById(commentId);
        if(opt.isPresent()){
           return opt.get();
        }
        throw new CommentException("No commnent found with this id");
    }
    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws CommentException, UserException {
        User user = userService.findUserById(userId);
        Comment comment = findCommentById(commentId);

        Userdto userdto = new Userdto();
        userdto.setId(user.getId());
        userdto.setUsername(user.getUsername());
        userdto.setName(user.getName());
        userdto.setEmail(user.getEmail());
        userdto.setUserImage(user.getImage());
        comment.getLikesByUsers().add(userdto);
        return commentRepository.save(comment);
    }
    @Override
    public Comment unlikeComment(Integer commentId, Integer userId) throws CommentException, UserException {
        User user = userService.findUserById(userId);
        Comment comment = findCommentById(commentId);

        Userdto userdto = new Userdto();
        userdto.setId(user.getId());
        userdto.setUsername(user.getUsername());
        userdto.setName(user.getName());
        userdto.setEmail(user.getEmail());
        userdto.setUserImage(user.getImage());
        comment.getLikesByUsers().remove(userdto);
        return commentRepository.save(comment);

    }
    @Override
    public boolean deleteComment(Integer commentId, Integer userId,Integer postId) throws CommentException, UserException {
        User user = userService.findUserById(userId);
        if(user!= null){
            throw new UserException("No user found with this ID");
        }
        Comment comment = findCommentById(commentId);
        if(comment!= null){
            commentRepository.delete(comment);
        }
        return true;
    }
}
