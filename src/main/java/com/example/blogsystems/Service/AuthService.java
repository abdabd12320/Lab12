package com.example.blogsystems.Service;

import com.example.blogsystems.Api.ApiException;
import com.example.blogsystems.Model.User;
import com.example.blogsystems.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public List<User> getUsers()
    {
        return authRepository.findAll();
    }

    public void register(User user)
    {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        authRepository.save(user);
    }

    public void update(Long id,User user)
    {
        User u = authRepository.findUserById(id);
        if(u == null)
        {
            throw new ApiException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        authRepository.save(u);
    }
    public void delete(Long id)
    {
        if(authRepository.findUserById(id) == null)
        {
            throw new ApiException("User not found");
        }
        authRepository.deleteById(id);
    }

}
