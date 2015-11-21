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

    public Date createdTime;
    public Date modifiedTime;

    public int avatar;
    public String userName;
}
