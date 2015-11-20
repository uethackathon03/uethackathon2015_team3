package com.unblievable.uetsupport.objects;

/**
 * Created by DucAnhZ on 20/11/2015.
 */
public class Teacher {

    public Long teacherId;
    public int avatar;

    public String fullname;
    public String email;
    public String phone;
    public String address;
    public String description;

    public String facultyName;

    public Teacher(String fullname, String phone) {
        this.fullname = fullname;
        this.phone = phone;
    }

    public int getAvatar() {

        return avatar;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }
}
