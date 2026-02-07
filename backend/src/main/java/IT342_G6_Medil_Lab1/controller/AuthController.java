package IT342_G6_Medil_Lab1.controller;

import IT342_G6_Medil_Lab1.model.User;
import IT342_G6_Medil_Lab1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // Allow React to talk to Spring
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // Create new user's account with encoded password
        User newUser = new User(user.getUsername(), user.getEmail(),
                passwordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElse(null);

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}