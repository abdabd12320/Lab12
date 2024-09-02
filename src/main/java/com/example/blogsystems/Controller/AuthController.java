package com.example.blogsystems.Controller;

import com.example.blogsystems.Api.ApiResponse;
import com.example.blogsystems.Model.User;
import com.example.blogsystems.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAuths()
    {
        return ResponseEntity.status(200).body(authService.getUsers());
    }
    @PostMapping("/register")
    public ResponseEntity getMyDetails(@Valid@RequestBody User user)
    {
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("Registered"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Long id,@Valid@RequestBody User user)
    {
        authService.update(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id)
    {
        authService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
    }
}
