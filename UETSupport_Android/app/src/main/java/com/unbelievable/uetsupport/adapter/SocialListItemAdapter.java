package com.unbelievable.uetsupport.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.unbelievable.uetsupport.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Nam on 11/21/2015.
 */
public class SocialListItemAdapter extends BaseAdapter {
    private Activity activity;
    private int layoutId;
    private ArrayList<com.unbelievable.uetsupport.objects.Thread> threads;

    public SocialListItemAdapter(Activity activity, int layoutId, ArrayList<com.unbelievable.uetsupport.objects.Thread> threads) {
        this.activity = activity;
        this.layoutId = layoutId;
        this.threads = threads;
    }

    ImageView avatar;
    TextView tvUserName;
    TextView tvUploadedTime;
    TextView tvContent;
    ImageView photo;
    Button btnLike;
    Button btnDisLike;
    Button btnComment;
    SimpleDateFormat format = new SimpleDateFormat("hh:mm dd:MM:yyyy");

    @Override
    public int getCount() {
        return threads.size();
    }

    @Override
    public Object getItem(int i) {
        return threads.get(i);
    }

    @Override
    public long getItemId(int i) {
        return threads.get(i).getThreadId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = activity.getLayoutInflater().inflate(layoutId, viewGroup, false);
        }
        initView(view);
<<<<<<< HEAD:UETSupport_Android/app/src/main/java/com/unblievable/uetsupport/adapter/SocialListItemAdapter.java
        com.unblievable.uetsupport.objects.Thread thread = threads.get(i);
        if (thread.isAnonimous) {
            avatar.setImageResource(R.mipmap.down);
            tvUserName.setText("Anonimous");
        } else {
            avatar.setImageResource(thread.getAvatar());
            tvUserName.setText(thread.getUserName());
        }
=======
        com.unbelievable.uetsupport.objects.Thread thread = threads.get(i);
        avatar.setImageResource(thread.getAvatar());
        tvUserName.setText(thread.getUserName());
>>>>>>> 79bf37e702c6741ff0530aee2b46a7d9e171ee75:UETSupport_Android/app/src/main/java/com/unbelievable/uetsupport/adapter/SocialListItemAdapter.java
        tvUploadedTime.setText(format.format(thread.getCreatedTime()));
        tvContent.setText(thread.getContent());
        if (thread.getPhotos()!= null) {
            photo.setImageResource(thread.getPhotos()[0]);
        } else photo.setVisibility(View.GONE);
        btnLike.setText(thread.getLike() + "");
        btnDisLike.setText(thread.getDisLike() + "");
        btnComment.setText(thread.getComment() + "");
        return view;
    }

    public void initView(View view) {
        avatar = (ImageView) view.findViewById(R.id.imgAvatar);
        tvUserName = (TextView) view.findViewById(R.id.tvUserName);
        tvUploadedTime = (TextView) view.findViewById(R.id.tvUploadedTime);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
        photo = (ImageView) view.findViewById(R.id.photo);
        btnLike = (Button) view.findViewById(R.id.btnLike);
        btnDisLike = (Button) view.findViewById(R.id.btnDislike);
        btnComment = (Button) view.findViewById(R.id.btnAnswer);
    }
}