package com.elmcgill.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private User author;

    @Column(name = "posted_date")
    private Date postedDate;

    private String content;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_likes_junction",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> likes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_reply_junction",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "reply_id")}
    )
    private Set<Post> replies;

    public Post() {
    }

    public Post(Long postId, User author, Date postedDate, String content, Set<User> likes, Set<Post> replies) {
        this.postId = postId;
        this.author = author;
        this.postedDate = postedDate;
        this.content = content;
        this.likes = likes;
        this.replies = replies;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Set<Post> getReplies() {
        return replies;
    }

    public void setReplies(Set<Post> replies) {
        this.replies = replies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(postId, post.postId) && Objects.equals(author, post.author) && Objects.equals(postedDate, post.postedDate) && Objects.equals(content, post.content) && Objects.equals(likes, post.likes) && Objects.equals(replies, post.replies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, author, postedDate, content, likes, replies);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", author=" + author +
                ", postedDate=" + postedDate +
                ", content='" + content + '\'' +
                ", likes=" + likes.size() +
                ", replies=" + replies +
                '}';
    }
}
