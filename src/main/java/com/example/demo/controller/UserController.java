package com.example.demo.controller;

import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;
    private final TokenUtil tokenUtil;
    //使用构造函数注入
    @Autowired
    public UserController(UserService userService, TokenUtil tokenUtil) {
        this.userService = userService;
        this.tokenUtil = tokenUtil;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // 检查用户名是否已存在
        User existingUsername = userService.getUserByUsername(user.getUsername());
        if (existingUsername != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        // 检查邮箱是否已存在
        User existingEmail = userService.getUserByEmail(user.getEmail());
        if (existingEmail != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        // 创建新用户
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    /*
    用于用户登录，根据提供的用户名和密码从数据库中检索用户。如果找到用户且密码正确，则返回用户对象和状态码 200；否则，返回状态码 401
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        // Retrieve user from database based on username and password
        User existingUser = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (existingUser != null) {
            // User found, generate JWT token
            String token = tokenUtil.generateToken(existingUser);
            // Return token to the frontend
            // Create and return LoginResponseDTO
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(token, existingUser.getUserId(), existingUser.getUsername(), existingUser.getEmail(),existingUser.getRole());
            return ResponseEntity.ok(loginResponseDTO);
        } else {
            // User not found or password incorrect
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


}