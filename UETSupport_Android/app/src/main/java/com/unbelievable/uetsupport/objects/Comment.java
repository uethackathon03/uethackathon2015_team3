package com.unbelievable.uetsupport.objects;

import java.util.Date;

/**
 * Created by Nam on 11/21/2015.
 */
public class Comment {
    public long commentId;
    public long threadId;
    public long userId;

    public String content;
    public Integer like;
    public Integer dislike;

    public Date createdTime;
    public Date modifiedTime;

    public int avatar;
    public String userName;

    public Comment(long commentId, String content, Integer like, Integer dislike, Date createdTime, Date modifiedTime, int avatar, String userName) {
        this.commentId = commentId;
        this.content = content;
        this.like = like;
        this.dislike = dislike;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.avatar = avatar;
        this.userName = userName;
    }
}
