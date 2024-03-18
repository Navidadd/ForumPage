package com.example.forumpage.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.forumpage.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    public User findUserById(Long id);

    @Query("SELECT username FROM User u WHERE u.id = ?1")
    public String getUsername(Long id);

    @Query("SELECT creationDate FROM User u WHERE u.id = ?1")
    public Date getCreationDateById(Long id);

}
