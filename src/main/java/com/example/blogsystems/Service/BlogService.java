package com.example.blogsystems.Service;

import com.example.blogsystems.Api.ApiException;
import com.example.blogsystems.Model.Blog;
import com.example.blogsystems.Model.User;
import com.example.blogsystems.Repository.AuthRepository;
import com.example.blogsystems.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAllBlogs()
    {
        return blogRepository.findAll();
    }
    public List<Blog> getMyBlogs(Long id)
    {
        User u = authRepository.findUserById(id);
        return blogRepository.findAllByUser(u);
    }
    public void addBlog(Long auth_id,Blog blog)
    {
        blog.setUser(authRepository.findUserById(auth_id));
        blogRepository.save(blog);
    }
    public void updateBlog(Long auth_id,Long blog_id,Blog blog)
    {
        Blog b = blogRepository.findBlogById(blog_id);

        if(authRepository.findUserById(auth_id) == null)
        {
            throw new ApiException("User not found");
        }
        if(blogRepository.findBlogById(blog_id) == null)
        {
            throw new ApiException("Blog not found");
        }
        if(blog_id != blog.getUser().getId())
        {
            throw new ApiException("Not match");
        }
        b.setTitle(blog.getTitle());
        b.setBody(blog.getBody());
        b.setUser(authRepository.findUserById(auth_id));
        blogRepository.save(b);
    }
    public void deleteBlog(Long auth_id,Long blog_id)
    {
        if(blogRepository.findBlogById(blog_id) == null)
        {
            throw new ApiException("Blog not found");
        }
        if(auth_id != blogRepository.findBlogById(blog_id).getUser().getId())
        {
            throw new ApiException("Not match");
        }
        blogRepository.deleteById(blog_id);
    }
    public Blog getBlogById(Long id,Long auth_id)
    {
        if(blogRepository.findBlogById(id) == null)
        {
            throw new ApiException("Blog not found");
        }
        if(auth_id != blogRepository.findBlogById(id).getUser().getId())
        {
            throw new ApiException("Not match");
        }
        return blogRepository.findBlogById(id);
    }
    public List<Blog> getBlogsByTitle(String title,Long auth_id)
    {
        if(blogRepository.findBlogByTitle(title) == null)
        {
            throw new ApiException("Blog not found");
        }
        if(auth_id != blogRepository.findBlogByTitle(title).get(0).getUser().getId())
        {
            throw new ApiException("Not match");
        }
        return blogRepository.findBlogByTitle(title);
    }
}
