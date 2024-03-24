package com.example.forumpage.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.forumpage.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    //@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    //@Query("SELECT u FROM User u WHERE u.id = ?1")
    public User findUserById(Integer id);

    @Query("SELECT username FROM User u WHERE u.id = ?1")
    public String getUsername(Integer id);

    @Query("SELECT creationDate FROM User u WHERE u.id = ?1")
    public Date getCreationDateById(Integer id);

    @Query("SELECT COUNT(f) FROM Follower f WHERE f.followedId = ?1")
    public Integer countByFollowedId(Integer id);

    @Query("SELECT COUNT(f) FROM Follower f WHERE f.followerId = ?1")
    public Integer countByFollowerId(Integer id);

    @Query(value = "INSERT INTO user_followers (followerId, followedId) VALUES (?1, ?2)", nativeQuery = true)
    public void addFollower(Integer followerId, Integer followedId);
}
