package com.example.forumpage.dto;

import java.sql.Date;
import java.util.List;

public class UserDto {

    private Integer id;
    private String username;
    private Date creationDate;
    private List<PostDto> posts;
    private List<Integer> followers;

    public UserDto() {
    }

    public UserDto(Integer id,String username, Date creationDate, List<PostDto> posts, List<Integer> followers) {
        this.id = id;
        this.username = username;
        this.creationDate = creationDate;
        this.posts = posts;
        this.followers = followers;
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

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
}
