package controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.*;

//import dto.LoginRequest;
//import dto.LogoutRequest;
//import dto.RegisterRequest;
//import dto.TimestampRequest;
//import dto.UpdateProfileRequest;
import services.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/session/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/session/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) {
        userService.logout(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getmyprofile")
    public ResponseEntity<?> getProfile(@RequestBody TimestampRequest request, Principal principal) {
        return ResponseEntity.ok(userService.getProfile(principal.getName()));
    }

    @PostMapping("/updatemyprofile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request, Principal principal) {
        userService.updateProfile(request, principal.getName());
        return ResponseEntity.ok().build();
    }
}
