package com.social.LongInstagram.controller;


import com.social.LongInstagram.entities.User;
import com.social.LongInstagram.exception.UserException;
import com.social.LongInstagram.sercurity.JwtTokenProvider;
import com.social.LongInstagram.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/user")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/id/{id}")
    public User findUserByIdHandler(@PathVariable Integer id) throws UserException {
        User user = userService.findUserById(id);
        return user;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findUserByUsernameHandler(@PathVariable String username) throws UserException{
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> findUserByEmailHandler(@PathVariable String email) throws UserException{
        User user = userService.findUserByEmail(email);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @GetMapping("/m/{userIds}")
    public ResponseEntity<List<User>> findUserByUserIdsHandler(@PathVariable List<Integer> userIds) throws UserException{
        List <User> users = userService.findAllUserByIds(userIds);
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>>searchUserHandler(@RequestParam("q") String query) throws UserException{
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }


}
