package com.social.LongInstagram.repository;


import com.social.LongInstagram.entities.Story;
import com.social.LongInstagram.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story , Integer> {
    List<Story> findStoriesByUserId(@Param("userId") Integer userId, LocalDateTime now);


}
