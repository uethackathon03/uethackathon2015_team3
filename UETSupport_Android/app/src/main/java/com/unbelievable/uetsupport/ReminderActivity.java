package com.unbelievable.uetsupport;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.unbelievable.uetsupport.objects.Reminder;

import java.util.ArrayList;

/**
 * Created by huylv on 21/11/2015.
 */
public class ReminderActivity extends AppCompatActivity {

    ListView reminderListView;
    ArrayList<Reminder> reminderArrayList;
    ReminderAdapter reminderAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remider);
        reminderArrayList = new ArrayList<>();
        reminderArrayList.add(new Reminder(new Long(1), 1000l, 1000l, 3, "title", "note"));
        reminderArrayList.add(new Reminder(new Long(2), 1000l, 1000l, 3, "title", "note"));
        reminderArrayList.add(new Reminder(new Long(3), 1000l, 1000l, 3, "title", "note"));

        reminderListView = (ListView) findViewById(R.id.reminderListView);
        reminderAdapter = new ReminderAdapter(this,R.layout.list_reminder_item,reminderArrayList);
        reminderListView.setAdapter(reminderAdapter);

        setTitle("Reminder");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class ReminderAdapter extends BaseAdapter {
        private Context context;
        private int layoutId;
        private ArrayList<Reminder> reminders;

        public ReminderAdapter(Context context, int layoutId, ArrayList<Reminder> reminders) {
            this.context = context;
            this.layoutId = layoutId;
            this.reminders = reminders;
        }

        @Override
        public int getCount() {
            return reminders.size();
        }

        @Override
        public Object getItem(int position) {
            return reminders.get(position);
        }

        @Override
        public long getItemId(int position) {
            return reminders.get(position).getReminderId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = getLayoutInflater().inflate(layoutId,parent,false);
            }
            ImageView imgReminderItem = (ImageView) convertView.findViewById(R.id.ivReminderItem);
            TextView tvReminderTitle = (TextView) convertView.findViewById(R.id.tvReminderTitle);
            TextView tvReminderTime = (TextView) convertView.findViewById(R.id.tvReminderTime);

            tvReminderTitle.setText(reminders.get(position).getTitle());
            tvReminderTime.setText(reminders.get(position).getTimeReminder() + "");
            return convertView;
        }
        //        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View rowView = inflater.inflate(R.layout.list_reminder_item, parent, false);
////            ImageView ivReminderItem = (ImageView)rowView.findViewById(R.id.ivProfileItem);
//            TextView tvReminderTitle = (TextView) rowView.findViewById(R.id.tvReminderTitle);
//            TextView tvReminderTime = (TextView) rowView.findViewById(R.id.tvReminderTime);
//
//            tvReminderTitle.setText(reminderArrayList.get(position).getTitle());
//            tvReminderTime.setText(reminderArrayList.get(position).getTimeReminder() + "");
//
//            return rowView;
//        }
//
    }


}
