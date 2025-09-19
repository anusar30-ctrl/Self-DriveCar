package com.car_rental.Car_Builder.controller;


import com.car_rental.Car_Builder.model.User;
import com.car_rental.Car_Builder.repository.UserRepository;
import com.car_rental.Car_Builder.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    // Constructor injection (recommended)
    public AuthController(UserRepository userRepo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setEmailId(user.getEmailId());
        userRepo.save(user);
        return "User registered!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User dbUser = userRepo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (encoder.matches(user.getPassword(), dbUser.getPassword())) {
            return jwtUtil.generateToken(dbUser.getUsername());
        }
        throw new RuntimeException("Invalid credentials");
    }

    @GetMapping("/usersList")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/usersList/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }


    @GetMapping("/usersList/emailid/{emailId}")
    public User getUserByEmail(@PathVariable String emailId) {
        return userRepo.findByEmailId(emailId)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + emailId));
    }

}
