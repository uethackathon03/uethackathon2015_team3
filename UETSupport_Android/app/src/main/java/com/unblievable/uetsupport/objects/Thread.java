package com.unblievable.uetsupport.objects;

import com.unblievable.uetsupport.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nam on 11/21/2015.
 */
public class Thread {
    public long threadId;
    public long userId;

    public String content;
    public int[] photos;
    public Integer like;
    public Integer disLike;
    public Integer comment;
    public boolean isAnonimous = Boolean.FALSE;

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

    public Thread(long threadId, String content, int[] photos, Integer like, Integer disLike, Integer comment, Date createdTime, boolean isAnonimous, String userName, int avatar) {
        this.threadId = threadId;
        this.content = content;
        this.photos = new int[photos.length];
        for (int i = 0;i < photos.length;i++){
            this.photos[i] = new Integer(photos[i]);
        }
        this.like = like;
        this.disLike = disLike;
        this.comment = comment;
        this.createdTime = createdTime;
        this.isAnonimous = isAnonimous;
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
    public boolean isAnonimous(){
        return isAnonimous;
    }
}
