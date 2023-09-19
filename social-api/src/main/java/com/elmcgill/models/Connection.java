package com.elmcgill.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "connections")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "connection_id")
    private Long connectionId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="followed", nullable = false)
    private User followedUser;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="following", nullable = false)
    private User followingUser;

    public Connection() {
    }

    public Connection(Long connectionId, User followedUser, User followingUser) {
        this.connectionId = connectionId;
        this.followedUser = followedUser;
        this.followingUser = followingUser;
    }

    public Long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Long connectionId) {
        this.connectionId = connectionId;
    }

    public User getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(User followedUser) {
        this.followedUser = followedUser;
    }

    public User getFollowingUser() {
        return followingUser;
    }

    public void setFollowingUser(User followingUser) {
        this.followingUser = followingUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Objects.equals(connectionId, that.connectionId) && Objects.equals(followedUser, that.followedUser) && Objects.equals(followingUser, that.followingUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionId, followedUser, followingUser);
    }

    @Override
    public String toString() {
        return "Connection{" +
                "connectionId=" + connectionId +
                ", followedUser=" + followedUser.getUserId() +
                ", followingUser=" + followingUser.getUserId() +
                '}';
    }
}
