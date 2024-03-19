package com.example.forumpage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forumpage.model.Follower;

@Repository
public interface UserFollowersRepository extends JpaRepository<Follower, Integer>{

}
