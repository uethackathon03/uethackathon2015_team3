package com.unbelievable.uetsupport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.unbelievable.uetsupport.adapter.CommentListAdapter;
import com.unbelievable.uetsupport.fragments.ProfileFragment;
import com.unbelievable.uetsupport.fragments.SocialFragment;
import com.unbelievable.uetsupport.objects.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Nam on 11/21/2015.
 */
public class CommentsActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    ListView commentList;
    CommentListAdapter commentAdapter;
    com.unbelievable.uetsupport.objects.Thread mainThread;
    ArrayList<Comment> comments;
    ImageView avatar;
    TextView tvUserName;
    TextView tvCreateTime;
    TextView tvContent;
    LinearLayout layout;
    ImageView photo;
    Button btnLike;
    Button btnDisLike;
    Button btnComment;
    SimpleDateFormat format = new SimpleDateFormat("hh:mm dd:MM:yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainThread = SocialFragment.sThread;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Comment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        avatar = (ImageView) findViewById(R.id.imgAvatar);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvCreateTime = (TextView) findViewById(R.id.tvUploadedTime);
        tvContent = (TextView) findViewById(R.id.tvContent);
        photo = (ImageView) findViewById(R.id.photo);
        layout = (LinearLayout) findViewById(R.id.threadLayout);
        btnLike = (Button) findViewById(R.id.btnLike);
        btnDisLike = (Button) findViewById(R.id.btnDislike);
        btnComment = (Button) findViewById(R.id.btnAnswer);
        commentList = (ListView) findViewById(R.id.listComment);
        avatar.setImageResource(mainThread.getAvatar());
        tvUserName.setText(mainThread.getUserName());
        tvCreateTime.setText(format.format(mainThread.getCreatedTime()));
        tvContent.setText(mainThread.getContent());
        btnLike.setText(mainThread.getLike() + "");
        btnDisLike.setText(mainThread.disLike + "");
        btnComment.setText(mainThread.getComment() + "");
        btnLike.setOnClickListener(this);
        btnDisLike.setOnClickListener(this);
        btnComment.setOnClickListener(this);
        if (mainThread.getPhotos() != null){
            photo.setImageResource(mainThread.getPhotos()[0]);
        }
        else photo.setVisibility(View.GONE);
        comments = new ArrayList<>();
        commentAdapter = new CommentListAdapter(this, mainThread.getComments(), R.layout.comment_item_list);
        commentList.setAdapter(commentAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLike){
            btnLike.setClickable(false);
            btnDisLike.setClickable(true);
            //TODO: Handle Like count
        }if (v == btnDisLike){
            btnLike.setClickable(true);
            btnDisLike.setClickable(false);
            //TODO: Handle Dislike count
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
