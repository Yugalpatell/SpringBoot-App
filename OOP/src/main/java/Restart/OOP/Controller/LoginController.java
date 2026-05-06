package Restart.OOP.Controller;

import Restart.OOP.Configuration.SecurityConfig;
import Restart.OOP.Model.User;
import Restart.OOP.Repository.UserRepository;
import Restart.OOP.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));

        user.setRole("ROLE_USER");

        userRepository.save(user);

        return "User Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        if(authentication.isAuthenticated()){
            return jwtUtil.generateToken(user.getUsername());
        } else {
            throw new RuntimeException("Invalid Credentials!");
        }
    }
}
