package com.social.LongInstagram.serviceImpl;



import com.social.LongInstagram.dto.Userdto;
import com.social.LongInstagram.entities.Story;
import com.social.LongInstagram.entities.User;
import com.social.LongInstagram.exception.StoryException;
import com.social.LongInstagram.exception.UserException;
import com.social.LongInstagram.repository.StoryRepository;
import com.social.LongInstagram.service.StoryService;
import com.social.LongInstagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceIpml implements StoryService {
    @Autowired
    private UserService userService;
    @Autowired
    private StoryRepository storyRepository;
    @Override
    public Story createStory(Story story, Integer userId) throws StoryException , UserException {
        User user = userService.findUserById(userId);
        if (user==null) {
            throw new UserException("User not found with this Id");
        }
        Userdto userdto = new Userdto();
        userdto.setId(user.getId());
        userdto.setEmail(user.getEmail());
        userdto.setName(user.getName());
        userdto.setUserImage(user.getImage());
        userdto.setUsername(user.getUsername());

        story.setUserdto(userdto);
        story.setCaption(story.getCaption());
        story.setTimeStamp(LocalDateTime.now());
        user.getStories().add(story);
        return storyRepository.save(story);
    }
    @Override
    public List<Story> findStoryByUserId(Integer userId) throws StoryException,UserException {
        User user = userService.findUserById(userId);
        if (user==null) {
            throw new UserException("User not found with this Id");
        }
        List<Story> stories = user.getStories();
        return stories;
    }
    @Override
    public Boolean deleteStory(Integer storyId, Integer userId) throws StoryException, UserException {
        // Tìm user theo userId
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new UserException("User not found with this ID: " + userId);
        }
        // Tìm story theo storyId
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new StoryException("Story not found!"));
        // Kiểm tra story có thuộc về user hay không
        if (!user.getStories().contains(story)) {
            throw new StoryException("The story does not belong to the user.");
        }
        // Xóa story khỏi danh sách stories của user
        user.getStories().remove(story);
        // Xóa story khỏi cơ sở dữ liệu
        storyRepository.delete(story);
        // Trả về true nếu xóa thành công
        return true;
    }
}
