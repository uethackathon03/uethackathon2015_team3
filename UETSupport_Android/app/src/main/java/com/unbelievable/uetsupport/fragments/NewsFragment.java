package com.unbelievable.uetsupport.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.unbelievable.uetsupport.R;
import com.unbelievable.uetsupport.adapter.AnnouceAdapter;
import com.unbelievable.uetsupport.adapter.NewsAdapter;
import com.unbelievable.uetsupport.objects.News;

import java.util.ArrayList;

/**
 * Created by Nam on 11/20/2015.
 */
public class NewsFragment extends Fragment implements View.OnClickListener {

    ListView newsListView;
    ArrayList<News> newsArrayList;
    ArrayList<News> annouceArrayList;
    NewsAdapter newsArrayAdapter;
    AnnouceAdapter annouceAdapter;

    Button btNewsSwitch;
    Button btAnnouceSwitch;

    public NewsFragment() {
        newsArrayList = new ArrayList<>();
        newsArrayList.add(new News("im1", "Noi dung tin tuc" + newsArrayList.size()));
        newsArrayList.add(new News("im1", "Noi dung tin tuc" + newsArrayList.size()));
        newsArrayList.add(new News("im1", "Noi dung tin tuc" + newsArrayList.size()));
        newsArrayList.add(new News("im1", "Noi dung tin tuc" + newsArrayList.size()));
        newsArrayList.add(new News("im1", "Noi dung tin tuc" + newsArrayList.size()));
        newsArrayList.add(new News("im1", "Noi dung tin tuc" + newsArrayList.size()));
        newsArrayList.add(new News("im1", "Noi dung tin tuc" + newsArrayList.size()));
        newsArrayList.add(new News("im1", "Noi dung tin tuc" + newsArrayList.size()));
        newsArrayList.add(new News("im1", "Noi dung tin tuc" + newsArrayList.size()));
        newsArrayList.add(new News("im1", "Noi dung tin tuc" + newsArrayList.size()));
        newsArrayList.add(new News("im1", "Noi dung tin tuc" + newsArrayList.size()));
        newsArrayList.add(new News("im1", "Noi dung tin tuc" + newsArrayList.size()));

        annouceArrayList = new ArrayList<>();
        annouceArrayList.add(new News("im1", "Noi dung tin tuc" + annouceArrayList.size()));
        annouceArrayList.add(new News("im1", "Noi dung tin tuc" + annouceArrayList.size()));
        annouceArrayList.add(new News("im1", "Noi dung tin tuc" + annouceArrayList.size()));
        annouceArrayList.add(new News("im1", "Noi dung tin tuc" + annouceArrayList.size()));
        annouceArrayList.add(new News("im1", "Noi dung tin tuc" + annouceArrayList.size()));
        annouceArrayList.add(new News("im1", "Noi dung tin tuc" + annouceArrayList.size()));
        annouceArrayList.add(new News("im1", "Noi dung tin tuc" + annouceArrayList.size()));
        annouceArrayList.add(new News("im1", "Noi dung tin tuc" + annouceArrayList.size()));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        newsListView = (ListView) v.findViewById(R.id.newslist);
        newsArrayAdapter = new NewsAdapter(getActivity(), newsArrayList);
        annouceAdapter = new AnnouceAdapter(getActivity(), annouceArrayList);
        newsListView.setAdapter(newsArrayAdapter);

        btNewsSwitch =(Button) v.findViewById(R.id.btNewsSwitch);
        btAnnouceSwitch = (Button)v.findViewById(R.id.btAnnouceSwitch);
        btNewsSwitch.setOnClickListener(this);
        btAnnouceSwitch.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btNewsSwitch:
                newsListView.setAdapter(newsArrayAdapter);
                break;
            case R.id.btAnnouceSwitch:
                newsListView.setAdapter(annouceAdapter);
                break;
        }
    }
}
