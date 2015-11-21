package com.unbelievable.uetsupport.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.pkmmte.view.CircularImageView;
import com.unbelievable.uetsupport.MainActivity;
import com.unbelievable.uetsupport.R;
import com.unbelievable.uetsupport.ReminderActivity;
import com.unbelievable.uetsupport.ScheduleActivity;
import com.unbelievable.uetsupport.common.Constant;

import java.util.ArrayList;

/**
 * Created by Nam on 11/20/2015.
 */
public class ProfileFragment extends Fragment implements AdapterView.OnItemClickListener{

    private CircularImageView profilePicture;
    private ImageView coverPhoto;
    private TextView tvName;
    private TextView tvVNUMail;
    private ListView profileListView;
    private ArrayList<String> profileArrayList;
    private ProfileAdapter profileAdapter;
    private DisplayImageOptions option;

    public ProfileFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        profilePicture = (CircularImageView) v.findViewById(R.id.profile_picture);
        coverPhoto = (ImageView)v.findViewById(R.id.cover_photo);
        tvName = (TextView)v.findViewById(R.id.tvName);
        tvVNUMail =(TextView)v.findViewById(R.id.tvVNUMail);

        profileArrayList = new ArrayList<String>();
        profileArrayList.add("Thời khóa biểu");
        profileArrayList.add("Lịch thi");
        profileArrayList.add("Kết quả học tập");
        profileArrayList.add("Nhắc nhở");
        profileArrayList.add("Câu hỏi");

        profileListView = (ListView)v.findViewById(R.id.profileListView);
        profileAdapter = new ProfileAdapter(getContext(),profileArrayList);
        profileListView.setAdapter(profileAdapter);
        profileListView.setOnItemClickListener(this);

        option = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.cover_photo2)
                .showImageOnFail(R.drawable.cover_photo2)
                .showImageOnLoading(R.drawable.cover_photo2)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .cacheInMemory(true).cacheOnDisk(true).build();

        IntentFilter filter = new IntentFilter("restart");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                onResume();
            }
        };
        getActivity().registerReceiver(broadcastReceiver, filter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        tvName.setText(getActivity().getSharedPreferences(Constant.nameSharedPreferences, Context.MODE_PRIVATE).getString(Constant.fullname, "Anonymous"));
        tvVNUMail.setText(getActivity().getSharedPreferences(Constant.nameSharedPreferences, Context.MODE_PRIVATE).getString(Constant.email, ""));
        try {
            ImageLoader.getInstance().displayImage(getActivity().getSharedPreferences(Constant.nameSharedPreferences, Context.MODE_PRIVATE).getString(Constant.photo, ""), coverPhoto, option);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                startActivity(intent);
                break;
            case 3:
                Intent i = new Intent(getActivity(), ReminderActivity.class);
                startActivity(i);
                break;
        }
    }

    private class ProfileAdapter extends ArrayAdapter<String> {

        ArrayList<String> list;
        public ProfileAdapter(Context context,ArrayList<String> l) {
            super(context, -1);
            list = l;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_profile_item,parent,false);
            ImageView ivNews = (ImageView)rowView.findViewById(R.id.ivProfileItem);
            TextView tvNews = (TextView) rowView.findViewById(R.id.tvProFileItem);

            switch (position){
                case 0:
                    ivNews.setImageResource(R.mipmap.tkb);
                    break;
                case 1:
                    ivNews.setImageResource(R.mipmap.lichthi);
                    break;
                case 2:
                    ivNews.setImageResource(R.mipmap.bangdiem);
                    break;
                case 3:
                    ivNews.setImageResource(R.mipmap.nhacnho);
                    break;
                case 4:
                    ivNews.setImageResource(R.mipmap.cauhoi);
                    break;
            }
            tvNews.setText(list.get(position));
            return rowView;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }
    }
}
