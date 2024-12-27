package com.social.LongInstagram.service;


import com.social.LongInstagram.entities.Story;
import com.social.LongInstagram.exception.StoryException;
import com.social.LongInstagram.exception.UserException;
import jakarta.persistence.Id;

import java.util.List;

public interface StoryService {
    public Story createStory(Story story , Integer userId) throws StoryException , UserException;
    public List<Story> findStoryByUserId(Integer userId) throws StoryException, UserException ;
    public Boolean deleteStory(Integer storeId , Integer userId) throws StoryException, UserException;

}
