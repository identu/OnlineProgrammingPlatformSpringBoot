package com.example.demo.service.impl;

import com.example.demo.dao.*;
import com.example.demo.model.*;
import com.example.demo.service.CompilationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserGradeRepository userGradeRepository;
    private final SubmissionRepository submissionRepository;
    private final FavoriteRepository favoriteRepository;


    // 使用构造函数注入
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserGradeRepository userGradeRepository, SubmissionRepository submissionRepository, ProblemRepository problemRepository, TestCaseRepository testCaseRepository, CompilationService compilationService, FavoriteRepository favoriteRepository) {
        this.userRepository = userRepository;
        this.userGradeRepository = userGradeRepository;
        this.submissionRepository = submissionRepository;
        this.favoriteRepository = favoriteRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user=userRepository.findByUserId(userId);
        if(user!=null){
            List<Favorite> favorites=favoriteRepository.findAllByUser(user);
            favoriteRepository.deleteAll(favorites);
            List<Submission> submissions=submissionRepository.findByUser(user);
            submissionRepository.deleteAll(submissions);
            List<UserGrade> userGrades=userGradeRepository.findByUser(user);
            userGradeRepository.deleteAll(userGrades);
        }

        userRepository.deleteById(userId);
    }



    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User updateUser(User user) {


        User existingUser = userRepository.findById(user.getUserId()).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            // 更新其他字段...
            return userRepository.save(existingUser);
        } else {
            return null; // 用户不存在
        }
    }
}
