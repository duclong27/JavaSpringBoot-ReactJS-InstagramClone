package com.social.LongInstagram.repository;


import com.social.LongInstagram.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository< User , Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u where u.id IN : users")
    public List<User> findAllUserByIds(@Param("users") List<Integer> userIds);
    @Query("SELECT DISTINCT u FROM User u Where u.username LIKE %:query% OR u.email LIKE %:query%" )
    public List<User>findByQuery(@Param("query")String query);

}
