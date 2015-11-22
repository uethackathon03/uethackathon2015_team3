package com.unbelievable.uetsupport.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.unbelievable.uetsupport.R;
import com.unbelievable.uetsupport.objects.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Nam on 11/21/2015.
 */
public class CommentListAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Comment> comments;
    private int layoutId;

    ImageView imgUserAvatar;
    TextView tvUserName;
    Button btnCommentLike;
    Button btnCommentDislike;
    TextView tvCommentContent;
    TextView tvCommentCreateTime;
    SimpleDateFormat format = new SimpleDateFormat("hh:mm dd/MM/yyyy");


    public CommentListAdapter(Activity activity, ArrayList<Comment> comments, int layoutId) {
        super();
        this.activity = activity;
        this.comments = comments;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return comments.get(position).commentId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(layoutId, parent, false);
        }
        initView(convertView);
        Comment comment = comments.get(position);
//        imgUserAvatar.setImageResource(comment.avatar);
        tvUserName.setText(comment.username);
//        btnCommentLike.setText(comment.like + "");
//        btnCommentDislike.setText(comment.dislike + "");
        tvCommentContent.setText(comment.content);
        tvCommentCreateTime.setText(format.format(comment.createdTime));
        return convertView;
    }

    public void initView(View v) {
        imgUserAvatar = (ImageView) v.findViewById(R.id.imgUserAvatar);
        tvUserName = (TextView) v.findViewById(R.id.tvUserName);
//        btnCommentLike = (Button) v.findViewById(R.id.btnCommentLike);
//        btnCommentDislike = (Button) v.findViewById(R.id.btnCommentDislike);
        tvCommentContent = (TextView) v.findViewById(R.id.tvCommentContent);
        tvCommentCreateTime = (TextView) v.findViewById(R.id.tvCommentCreateTime);
    }
}
