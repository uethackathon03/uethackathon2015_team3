package com.unblievable.uetsupport.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.unblievable.uetsupport.R;
import com.unblievable.uetsupport.common.RoundedImageView;
import com.unblievable.uetsupport.objects.Office;
import com.unblievable.uetsupport.objects.Teacher;

import java.util.ArrayList;

/**
 * Created by Nam on 11/21/2015.
 */
public class ListProfAdapter extends BaseAdapter {
    final int PROF_MODE = 0;
    final int OFFICE_MODE = 1;
    private Activity activity;
    private int layoutId = R.layout.list_prof;
    private ArrayList<Teacher> p;
    private ArrayList<Office> o;
    private int mode;

    RoundedImageView imgAva;
    TextView tvName;
    TextView tvPhone;


    public ListProfAdapter(Activity activity, ArrayList<Teacher> p, ArrayList<Office> o, int mode) {
        this.activity = activity;
        this.p = p;
        this.o = o;
        this.mode = mode;
    }


    @Override
    public int getCount() {
        return p.size();
    }

    @Override
    public Object getItem(int position) {
        return p.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(layoutId, parent, false);
        }
        imgAva = (RoundedImageView) convertView.findViewById(R.id.imgAva);
        tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
        if (mode == PROF_MODE) {
            imgAva.setVisibility(View.VISIBLE);
//            imgAva.setImageResource(p.get(position).getAvatar());
            tvName.setText(p.get(position).getFullname());
            tvPhone.setText(p.get(position).getPhone());
        } else if (mode == OFFICE_MODE) {
            imgAva.setVisibility(View.GONE);
            tvName.setText(o.get(position).getName());
            tvPhone.setText(o.get(position).getPhone());
        }
        return convertView;
    }
}
