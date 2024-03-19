package com.example.forumpage.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "Debe tener un formato válido")
    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;
     
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "Debe contener minimo 8 caracteres")
    @Column(name = "password", nullable = false, length = 64)
    private String password;
     
    @NotBlank(message = "Nombre de usuario es obligatorio")
    @Size(min = 3, message = "Debe contener minimo 3 caracteres")
    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "creation_date")
    private Date creationDate;

    private List<Integer> followers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

}
