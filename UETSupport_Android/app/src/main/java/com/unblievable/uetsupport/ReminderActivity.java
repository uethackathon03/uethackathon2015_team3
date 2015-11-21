package com.unblievable.uetsupport;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.unblievable.uetsupport.objects.Reminder;

import java.util.ArrayList;

/**
 * Created by huylv on 21/11/2015.
 */
public class ReminderActivity extends AppCompatActivity {

    ListView reminderListView;
    ArrayList<Reminder> reminderArrayList;
    ReminderAdapter reminderAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        reminderArrayList = new ArrayList<>();
        reminderArrayList.add(new Reminder(1, 1000l, 1000l, 3, "title", "note"));
        reminderArrayList.add(new Reminder(2, 1000l, 1000l, 3, "title", "note"));
        reminderArrayList.add(new Reminder(3, 1000l, 1000l, 3, "title", "note"));

        reminderListView  = (ListView)findViewById(R.id.reminderListView);
        reminderAdapter = new ReminderAdapter(this);
        reminderListView.setAdapter(reminderAdapter);

        setTitle("Reminder");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class ReminderAdapter extends ArrayAdapter<Reminder> {
        public ReminderAdapter(Context context) {
            super(context, -1);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_reminder_item,parent,false);
//            ImageView ivReminderItem = (ImageView)rowView.findViewById(R.id.ivProfileItem);
            TextView tvReminderTitle = (TextView) rowView.findViewById(R.id.tvReminderTitle);
            TextView tvReminderTime = (TextView)rowView.findViewById(R.id.tvReminderTime);

            tvReminderTitle.setText(reminderArrayList.get(position).getTitle());
            tvReminderTime.setText(reminderArrayList.get(position).getTimeReminder()+"");

            return rowView;
        }

        @Override
        public int getCount() {
            return reminderArrayList.size();
        }
    }



}
