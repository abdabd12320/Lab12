package com.example.blogsystems.Repository;

import com.example.blogsystems.Model.Blog;
import com.example.blogsystems.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    Blog findBlogById(Long id);

    List<Blog> findAllByUser(User user);

    List<Blog> findBlogByTitle(String title);
}
