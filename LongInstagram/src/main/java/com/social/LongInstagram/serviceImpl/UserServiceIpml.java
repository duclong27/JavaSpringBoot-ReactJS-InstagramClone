package com.social.LongInstagram.serviceImpl;


import com.social.LongInstagram.dto.AuthenticationRequest;
import com.social.LongInstagram.dto.Userdto;
import com.social.LongInstagram.entities.User;
import com.social.LongInstagram.exception.UserException;
import com.social.LongInstagram.repository.UserRepository;
import com.social.LongInstagram.sercurity.JwtTokenClaims;
import com.social.LongInstagram.sercurity.JwtTokenProvider;
import com.social.LongInstagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIpml implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public User registerUser(User user) throws UserException {
        Optional<User> opt = userRepository.findByEmail(user.getEmail());
        if(opt.isPresent()){
            throw new UserException("Email is already exist");
        }
        Optional<User> isUsernameExist = userRepository.findByUsername(user.getUsername());
        if(isUsernameExist.isPresent()){
            throw  new UserException("Username is already exists");
        }
        if(user.getEmail()==null || user.getUsername() ==null || user.getName()==null ||  user.getPassword()==null){
            throw new UserException("Invalid data");
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setUsername(user.getUsername());
        newUser.setName(user.getName());
        System.out.println(" This is new user " + newUser);
        return userRepository.save(newUser);
    }
    @Override
    public User findUserById(Integer id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }else {
            throw  new UserException("user not found with this id" + id);
        }
    }
    @Override
    public User findUserProfile(String token) throws UserException {
        token = token.substring(7);
        JwtTokenClaims jwtTokenClaims = jwtTokenProvider.getClaimsFromToken(token);
        String email = jwtTokenClaims.getEmail();
        Optional<User> opt = userRepository.findByEmail(email);
        if(opt.isPresent()){
            opt.get();
        }

        throw new UserException("Invalidate email");
    }
    @Override
    public User findUserByUsername(String username) throws UserException {
        Optional<User> opt = userRepository.findByUsername(username);
        if(opt.isPresent()){
            opt.get();
        }
        throw new UserException("User not foung with this username" +username);
    }
    @Override
    public User findUserByEmail(String email) throws UserException {
        Optional<User> opt = userRepository.findByEmail(email);
      if(opt.isPresent()){
          opt.get();
      }
        throw new UserException("User not foung with this email" + email);
    }

    @Override
    public String followUser(Integer reqUserId, Integer followerUserId) throws UserException {

        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(followerUserId);

        Userdto follower = new Userdto();
        follower.setEmail(reqUser.getEmail());
        follower.setName(reqUser.getName());
        follower.setUsername(reqUser.getUsername());
        follower.setUserImage(reqUser.getUsername());
        follower.setId(reqUser.getId());

        Userdto following = new Userdto();
        following.setEmail(reqUser.getEmail());
        following.setName(reqUser.getName());
        following.setUsername(reqUser.getUsername());
        following.setUserImage(reqUser.getUsername());
        following.setId(reqUser.getId());

        reqUser.getFollowing().add(following);
        followUser.getFollowers().add(follower);

        userRepository.save(followUser);
        userRepository.save(reqUser);

        return "you are following  " + follower.getUsername();
    }

    @Override
    public String unfollowUser(Integer reqUserId, Integer followerUserId) throws UserException {
        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(followerUserId);

        Userdto follower = new Userdto();
        follower.setEmail(reqUser.getEmail());
        follower.setName(reqUser.getName());
        follower.setUsername(reqUser.getUsername());
        follower.setUserImage(reqUser.getUsername());
        follower.setId(reqUser.getId());

        Userdto following = new Userdto();
        following.setEmail(reqUser.getEmail());
        following.setName(reqUser.getName());
        following.setUsername(reqUser.getUsername());
        following.setUserImage(reqUser.getUsername());
        following.setId(reqUser.getId());

        reqUser.getFollowing().remove(following);
        followUser.getFollowers().remove(follower);

        userRepository.save(followUser);
        userRepository.save(reqUser);

        return "you are following  " + follower.getUsername();
    }
    @Override
    public List<User> searchUser(String query) throws UserException {
        List<User> users =userRepository.findByQuery(query);
        if(users.size()==0){
            throw new UserException("User not found");
        }
        return users;
    }
    @Override
    public List<User> findAllUserByIds(List<Integer> userIds) throws UserException {
        List<User> users = userRepository.findAllUserByIds(userIds);
        return users;
    }

    @Override
    public User updateUserDetails(User updatedUser, User existingUser) {
        if(updatedUser.getEmail() != null){
            existingUser.setEmail(updatedUser.getEmail());
        }
        if(updatedUser.getUsername() != null){
            existingUser.setUsername(updatedUser.getUsername());
        }
        if(updatedUser.getName() != null){
            existingUser.setUsername(updatedUser.getName());
        }
        if(updatedUser.getPhoneNumber() != null){
            existingUser.setUsername(updatedUser.getPhoneNumber());
        }
        if(updatedUser.getMobile() != null){
            existingUser.setMobile(updatedUser.getMobile());
        }
        if(updatedUser.getWebsite() != null){
            existingUser.setWebsite(updatedUser.getWebsite());
        }
        if(updatedUser.getGender() != null){
            existingUser.setGender(updatedUser.getGender());
        }
        if(updatedUser.getId().equals(existingUser.getId())){
            userRepository.save(updatedUser);
        }
        return updatedUser;
    }

}
