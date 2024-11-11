
package services;  // Change the package to match your project structure

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;  // Add this import
//import org.springframework.security.core.userdetails.User as SecurityUser;  // Add this import

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dto.LoginRequest;
import dto.LogoutRequest;
import dto.RegisterRequest;
import dto.UpdateProfileRequest;
import models.User;
import repo.UserRepository;
import util.JwtTokenUtil;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Map<String, Object> register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDisplayusername(request.getDisplayusername());
        
        user = userRepository.save(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("displayusername", user.getDisplayusername());
        response.put("userid", user.getUserid());
        return response;
    }

    public Map<String, Object> login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        
        String token = jwtTokenUtil.generateToken(loadUserDetails(user));
        
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("displayusername", user.getDisplayusername());
        response.put("userid", user.getUserid());
        return response;
    }

    public void logout(LogoutRequest request) {
        // Token blacklisting could be implemented here
    }

    public Map<String, Object> getProfile(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        Map<String, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("displayusername", user.getDisplayusername());
        response.put("userid", user.getUserid());
        return response;
    }

    public void updateProfile(UpdateProfileRequest request, String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        user.setDisplayusername(request.getDisplayusername());
        userRepository.save(user);
    }
    
 // Remove the problematic import
 // import org.springframework.security.core.userdetails.User as SecurityUser;

 private UserDetails loadUserDetails(User user) {
     return new org.springframework.security.core.userdetails.User(  // Use fully qualified name
         user.getUsername(),
         user.getPassword(),
         new ArrayList<>()
     );
 }
}
































//package services;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.tools.DocumentationTool;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//
//import dto.LoginRequest;
//import dto.LogoutRequest;
//import dto.RegisterRequest;
//import dto.UpdateProfileRequest;
//import models.User;
//import repo.UserRepository;
//import util.JwtTokenUtil;
//
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    public Map<String, Object> register(RegisterRequest request) {
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setDisplayusername(request.getDisplayusername());
//        
//        user = userRepository.save(user);
//        
//        Map<String, Object> response = new HashMap<>();
//        response.put("displayusername", user.getDisplayusername());
//        response.put("userid", user.getUserid());
//        return response;
//    }
//
//    public Map<String, Object> login(LoginRequest request) {
//        User user = userRepository.findByUsername(request.getUsername())
//            .orElseThrow(() -> new RuntimeException("User not found"));
//            
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//        
//        String token = jwtTokenUtil.generateToken(loadUserDetails(user));
//        
//        Map<String, Object> response = new HashMap<>();
//        response.put("token", token);
//        response.put("displayusername", user.getDisplayusername());
//        response.put("userid", user.getUserid());
//        return response;
//    }
//
//    public void logout(LogoutRequest request) {
//        // Token blacklisting could be implemented here
//    }
//
//    public Map<String, Object> getProfile(String username) {
//        User user = userRepository.findByUsername(username)
//            .orElseThrow(() -> new RuntimeException("User not found"));
//            
//        Map<String, Object> response = new HashMap<>();
//        response.put("username", user.getUsername());
//        response.put("displayusername", user.getDisplayusername());
//        response.put("userid", user.getUserid());
//        return response;
//    }
//
//    public void updateProfile(UpdateProfileRequest request, String username) {
//        User user = userRepository.findByUsername(username)
//            .orElseThrow(() -> new RuntimeException("User not found"));
//        user.setDisplayusername(request.getDisplayusername());
//        userRepository.save(user);
//    }
//    
//    private UserDetails loadUserDetails(User user) {
//        return new models.springframework.security.core.userdetails.User(
//            user.getUsername(),
//            user.getPassword(),
//            new ArrayList<>()
//        );
//    }
//}