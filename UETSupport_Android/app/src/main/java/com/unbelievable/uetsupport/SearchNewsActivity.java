package com.unbelievable.uetsupport;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by huylv on 21/11/2015.
 */
public class SearchNewsActivity extends AppCompatActivity{
    ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_search_news);
    }
}
