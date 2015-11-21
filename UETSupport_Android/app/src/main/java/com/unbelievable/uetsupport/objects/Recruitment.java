package com.unbelievable.uetsupport.objects;

import com.unbelievable.uetsupport.common.CommonUtils;

import org.json.JSONObject;

/**
 * Created by Nam on 11/21/2015.
 */
public class Recruitment {
    public String title;
    public String content;
    public String logo;
    public String createdTime;

    public Recruitment() {}

    public static Recruitment getNews(JSONObject jData) {
        try {
            Recruitment recruitment = new Recruitment();
            recruitment.title = CommonUtils.getValidString(jData.getString("title"));
            recruitment.content = CommonUtils.getValidString(jData.getString("content"));
            recruitment.logo = CommonUtils.getValidString(jData.getString("photo"));
            recruitment.createdTime = CommonUtils.getValidString(jData.getString("createdTime"));
            return recruitment;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
