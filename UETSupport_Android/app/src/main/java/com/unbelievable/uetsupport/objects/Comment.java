package com.unbelievable.uetsupport.objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Nam on 11/21/2015.
 */
public class Comment {
    public long commentId;
    public long threadId;
    public long userID;

    public String content;
    public String photo;
    public boolean isCorrect;
    public String  username;
    public String avatar;
    public String createdTime;
    public String modifiedTime;

    public Comment() {}

    public static Comment getComment(JSONObject jData){
        try {
            Comment comment = new Comment();
            comment.commentId = jData.getLong("commentId");
            comment.content = jData.getString("contend");
            comment.photo = jData.getString("photo");
            comment.username = jData.getString("username");
            comment.avatar = jData.getString("avatar");
            comment.createdTime = jData.getString("createdTime");
            comment.modifiedTime = jData.getString("modifiedTime");
            return comment;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
