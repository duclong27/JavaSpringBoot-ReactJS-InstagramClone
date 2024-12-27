package com.social.LongInstagram.controller;


import com.social.LongInstagram.entities.Story;
import com.social.LongInstagram.entities.User;
import com.social.LongInstagram.exception.PostException;
import com.social.LongInstagram.exception.StoryException;
import com.social.LongInstagram.exception.UserException;
import com.social.LongInstagram.service.StoryService;
import com.social.LongInstagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/story")
@Controller
public class StoryController {

    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;

    @PostMapping("/createStory")
    public ResponseEntity<Story> createStory( Story story, @RequestHeader("Authorization") String token) throws StoryException, UserException{
        User user = userService.findUserProfile(token);
        Story createdStory = storyService.createStory( story ,user.getId());
        return new ResponseEntity<Story>(createdStory, HttpStatus.OK);
    }

    @GetMapping("/getStory/{storyId}")
    public ResponseEntity<List<Story>> getStory( Integer userId) throws StoryException,UserException{
        List<Story> stories = storyService .findStoryByUserId(userId);
        return new ResponseEntity<List<Story>>( stories, HttpStatus.OK);
    }

    @GetMapping("/deleteStory")
    public ResponseEntity<String> deleteStory( Integer storyId, @RequestHeader("Authorization") String token) throws StoryException, UserException{
        User user = userService.findUserProfile(token);
        Boolean deletedStory = storyService.deleteStory(storyId, user.getId());
        if (!deletedStory) {
            throw new StoryException("Story deleted unsuccessfully");
        }
        return new ResponseEntity<>("Story was deleted successful", HttpStatus.OK);
    }

}
