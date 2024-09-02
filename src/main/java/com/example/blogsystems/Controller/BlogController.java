package com.example.blogsystems.Controller;

import com.example.blogsystems.Api.ApiResponse;
import com.example.blogsystems.Model.Blog;
import com.example.blogsystems.Model.User;
import com.example.blogsystems.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/blog")
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get-all-blogs")
    public ResponseEntity getAllBlogs()
    {
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }
    @GetMapping("/get-my-blogs")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(blogService.getMyBlogs(user.getId()));
    }
    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user,@Valid@RequestBody Blog blog)
    {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog added"));
    }
    @PutMapping("/update/{blogID}")
    public ResponseEntity updateBlog(@PathVariable Long blogID,@AuthenticationPrincipal User user,@Valid@RequestBody Blog blog)
    {
        blogService.updateBlog(blogID, user.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog updated"));
    }
    @DeleteMapping("/delete/{blogID}")
    public ResponseEntity deleteBlog(@PathVariable Long blogID,@AuthenticationPrincipal User user)
    {
        blogService.deleteBlog(blogID, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Blog updated"));
    }
    @GetMapping("/get-blog-by-id/{id}")
    public ResponseEntity getBlogById(@PathVariable Long id,@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(blogService.getBlogById(id, user.getId()));
    }
    @GetMapping("/get-blog-by-title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title,@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(blogService.getBlogsByTitle(title, user.getId()));
    }

}
