package com.unbelievable.uetsupport.objects;

import com.unbelievable.uetsupport.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nam on 11/21/2015.
 */
public class Thread {
    public long threadId;
    public long userId;

    public String content;
    public int[] photos = {R.mipmap.logo_uet};
    public Integer like;
    public Integer disLike;
    public Integer comment;
    public boolean isAnonimous;

    public Date createdTime;
    public Date modifiedTime;

    public ArrayList<Comment> comments;
    public String userName;
    public int avatar;

    public Thread(long threadId, String content, Integer like, Integer disLike, Integer comment, Date createdTime, String userName, int avatar) {
        this.threadId = threadId;
        this.content = content;
        this.like = like;
        this.disLike = disLike;
        this.comment = comment;
        this.createdTime = createdTime;
        this.userName = userName;
        this.avatar = avatar;
    }

    public int[] getPhotos() {
        return photos;
    }

    public long getThreadId() {

        return threadId;
    }

    public String getContent() {
        return content;
    }

    public Integer getLike() {
        return like;
    }

    public Integer getDisLike() {
        return disLike;
    }

    public Integer getComment() {
        return comment;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getUserName() {
        return userName;
    }

    public int getAvatar() {
        return avatar;
    }
}
