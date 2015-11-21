package com.unbelievable.uetsupport.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unbelievable.uetsupport.R;
import com.unbelievable.uetsupport.adapter.SocialListItemAdapter;
import com.unbelievable.uetsupport.objects.*;
import com.unbelievable.uetsupport.objects.Thread;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nam on 11/20/2015.
 */
public class SocialFragment extends Fragment{
    ListView socialListItem;
    ArrayList<com.unbelievable.uetsupport.objects.Thread> threads;
    SocialListItemAdapter itemAdapter;
    int[] photos = {R.mipmap.logo_uet};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_social,container,false);
        socialListItem = (ListView) v.findViewById(R.id.list_item);
        threads = new ArrayList<>();
        for (int i = 0;i < 5;i++){
            threads.add(new Thread(2*i+1,"Có ai làm hộ mình bài này với",1,3,4,new Date(System.currentTimeMillis()),"bboy",R.mipmap.user));
            threads.add(new Thread(2*i+2,"Tìm nội suy của hàm số sau:",photos,5,1,2,new Date(System.currentTimeMillis()),true,"Stupid boy",R.mipmap.user));
        }
        itemAdapter = new SocialListItemAdapter(this.getActivity(),R.layout.social_item_list,threads);
        socialListItem.setAdapter(itemAdapter);
        return v;
    }
}
