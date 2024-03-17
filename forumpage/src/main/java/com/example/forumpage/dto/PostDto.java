package com.example.forumpage.dto;

import java.sql.Date;

public class PostDto {

    private Long id;
    private String title;
    private String username;
    private Date creationDate;
    private String body;
    private Long idUser;
    public PostDto(){

    }

    public PostDto(String title, String username, Date creationDate, String body) {
        this.title = title;
        this.username = username;
        this.creationDate = creationDate;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
