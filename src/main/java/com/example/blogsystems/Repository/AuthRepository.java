package com.example.blogsystems.Repository;

import com.example.blogsystems.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Long> {

    User findUserById(Long id);

    User findUserByUsername(String username);
}
