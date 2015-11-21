package com.unbelievable.uetsupport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unbelievable.uetsupport.R;
import com.unbelievable.uetsupport.objects.News;

import java.util.ArrayList;

/**
 * Created by huylv on 20/11/2015.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    Context context;
    ArrayList<News> newsArrayList;

    public NewsAdapter(Context context, ArrayList<News> newsArrayList) {
        super(context, -1, newsArrayList);
        this.context=context;
        this.newsArrayList= newsArrayList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_news_item,parent,false);
        ImageView ivNews = (ImageView)rowView.findViewById(R.id.imNews);
        TextView tvNews = (TextView) rowView.findViewById(R.id.tvNews);
        LinearLayout ll = (LinearLayout)rowView.findViewById(R.id.pageView);

            if (position == 0) {
                ll.setVisibility(View.VISIBLE);
                ivNews.setVisibility(View.GONE);
                tvNews.setVisibility(View.GONE);
                ivNews.setImageResource(R.drawable.e1);
                tvNews.setText(newsArrayList.get(position).getContentNews());
            }
            tvNews.setText(newsArrayList.get(position).getContentNews());

        return rowView;
    }

    @Override
    public int getCount() {
        return newsArrayList.size();
    }
}