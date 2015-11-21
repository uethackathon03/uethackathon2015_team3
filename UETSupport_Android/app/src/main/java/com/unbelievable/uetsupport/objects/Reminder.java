package com.unbelievable.uetsupport.objects;

/**
 * Created by DucAnhZ on 20/11/2015.
 */
public class Reminder {

    public Integer reminderId;
    public Long timeReminder;
    public Long beforeReminder;
    public Integer numberOfReminder;
    public String title;
    public String note;

    public Reminder(Integer reminderId, Long timeReminder, Long beforeReminder, Integer numberOfReminder, String title, String note) {
        this.reminderId = reminderId;
        this.timeReminder = timeReminder;
        this.beforeReminder = beforeReminder;
        this.numberOfReminder = numberOfReminder;
        this.title = title;
        this.note = note;
    }

    public Integer getReminderId() {
        return reminderId;
    }

    public void setReminderId(Integer reminderId) {
        this.reminderId = reminderId;
    }

    public Long getTimeReminder() {
        return timeReminder;
    }

    public void setTimeReminder(Long timeReminder) {
        this.timeReminder = timeReminder;
    }

    public Long getBeforeReminder() {
        return beforeReminder;
    }

    public void setBeforeReminder(Long beforeReminder) {
        this.beforeReminder = beforeReminder;
    }

    public Integer getNumberOfReminder() {
        return numberOfReminder;
    }

    public void setNumberOfReminder(Integer numberOfReminder) {
        this.numberOfReminder = numberOfReminder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
