package com.example.forumpage.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "follower_id")
    private Integer followerId;

    @Column(name = "followed_id")
    private Integer followedId;

    @Column(name = "followed_date")
    private Date followerDate;

    public Follower(Integer id, Integer followerId, Integer followedId, Date followerDate) {
        this.id = id;
        this.followerId = followerId;
        this.followedId = followedId;
        this.followerDate = followerDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public Integer getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Integer followedId) {
        this.followedId = followedId;
    }

    public Date getFollowerDate() {
        return followerDate;
    }

    public void setFollowerDate(Date followerDate) {
        this.followerDate = followerDate;
    }
 
}
