package com.unbelievable.uetsupport.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unbelievable.uetsupport.R;
import com.unbelievable.uetsupport.adapter.NotificationAdapter;
import com.unbelievable.uetsupport.objects.Notification;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nam on 11/20/2015.
 */
public class NotificationFragment extends Fragment {
    ListView listNotification;
    NotificationAdapter adapter;
    ArrayList<Notification> notificationArrayList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu,container,false);
        listNotification = (ListView) v.findViewById(R.id.listNotification);
        notificationArrayList = new ArrayList<>();
        for (int i = 0;i <5;i++){
            notificationArrayList.add(new Notification("Thông báo","Nội dung thông báo",false,new Date(System.currentTimeMillis())));
            notificationArrayList.add(new Notification("Thông báo rất quan trọng","Nội dung cực kì quan trong ",true,new Date(System.currentTimeMillis())));
        }
        adapter = new NotificationAdapter(getContext(),notificationArrayList);
        listNotification.setAdapter(adapter);
        return v;
    }
}
