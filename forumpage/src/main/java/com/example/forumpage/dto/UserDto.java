package com.example.forumpage.dto;

import java.sql.Date;
import java.util.List;

public class UserDto {

    private String username;
    private Date creationDate;
    private List<PostDto> posts;

    public UserDto(String username, Date creationDate, List<PostDto> posts) {
        this.username = username;
        this.creationDate = creationDate;
        this.posts = posts;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public List<PostDto> getPosts() {
        return posts;
    }
    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }

    
    
}
