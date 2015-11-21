package com.unbelievable.uetsupport;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DucAnhZ on 21/11/2015.
 */
public class ScheduleActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView scheduleListView;
    ArrayList<String> scheduleArrayList;
    ScheduleAdapter scheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Thời khóa biểu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prepareList();
        scheduleListView = (ListView)findViewById(R.id.scheduleListView);
        scheduleAdapter = new ScheduleAdapter(this);
        scheduleListView.setAdapter(scheduleAdapter);



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


    private class ScheduleAdapter extends ArrayAdapter<String>{

        public ScheduleAdapter(Context context) {
            super(context, -1);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(android.R.layout.simple_list_item_1,parent,false);
            TextView tv = (TextView)rowView.findViewById(android.R.id.text1);

            tv.setText(scheduleArrayList.get(position));
            return rowView;
        }

        @Override
        public int getCount() {
            return scheduleArrayList.size();
        }
    }

    void prepareList(){
        scheduleArrayList = new ArrayList<>();
        scheduleArrayList.add("1. Giải tích 1 - 301-G2");
        scheduleArrayList.add("2. Cầu lông");
        scheduleArrayList.add("3. Tiếng Anh B1 - 304-GĐ2");
        scheduleArrayList.add("4. Đại số - 309-GĐ2");
        scheduleArrayList.add("5. Tiếng Anh B1 - 304-GĐ2");
        scheduleArrayList.add("6. Đại số BT - 309-GĐ2");
        scheduleArrayList.add("8. Cơ sở dữ liệu B1 - 309-G2");
        scheduleArrayList.add("9. Cơ sở dữ liệu TH - 201-G2");
        scheduleArrayList.add("10. Mạng máy tính - 304-GĐ2");

    }
}
