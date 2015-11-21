package com.unbelievable.uetsupport.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.loopj.android.http.TextHttpResponseHandler;
import com.unbelievable.uetsupport.MainActivity;
import com.unbelievable.uetsupport.R;
import com.unbelievable.uetsupport.adapter.RecruitmentAdapter;
import com.unbelievable.uetsupport.adapter.NewsAdapter;
import com.unbelievable.uetsupport.common.CommonUtils;
import com.unbelievable.uetsupport.common.UETSupportUtils;
import com.unbelievable.uetsupport.objects.News;
import com.unbelievable.uetsupport.service.CustomAsyncHttpClient;
import com.unbelievable.uetsupport.service.Service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Nam on 11/20/2015.
 */
public class NewsFragment extends Fragment implements View.OnClickListener {

    private ListView newsListView;
    private ArrayList<News> annouceArrayList;
    private NewsAdapter newsArrayAdapter;
    private RecruitmentAdapter recruitmentAdapter;
    private MainActivity mainActivity;

    private Button btNewsSwitch;
    private Button btRecruitmentSwitch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        mainActivity = (MainActivity) getActivity();
        newsListView = (ListView) v.findViewById(R.id.newslist);
        newsArrayAdapter = new NewsAdapter(getActivity(), mainActivity.newsArrayList);
        recruitmentAdapter = new RecruitmentAdapter(getActivity(), mainActivity.recruitmentArrayList);
        newsListView.setAdapter(newsArrayAdapter);


        btNewsSwitch =(Button) v.findViewById(R.id.btNewsSwitch);
        btRecruitmentSwitch = (Button)v.findViewById(R.id.btAnnouceSwitch);
        btNewsSwitch.setOnClickListener(this);
        btRecruitmentSwitch.setOnClickListener(this);
        if (mainActivity.newsArrayList.size() == 0) {
            News news = new News();
            mainActivity.newsArrayList.add(news);
            parseNewsFromServer();
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btNewsSwitch:
                newsListView.setAdapter(newsArrayAdapter);
                break;
            case R.id.btAnnouceSwitch:
                newsListView.setAdapter(recruitmentAdapter);
                break;
        }
    }
    //TODO
    private void parseNewsFromServer() {
        if(!UETSupportUtils.networkConnected(getActivity())) {
            return;
        }

        CustomAsyncHttpClient client = new CustomAsyncHttpClient(getActivity(), "");
        String url = Service.ServerURL + "/data/informations";
        client.get(url, new TextHttpResponseHandler() {
            private ProgressDialog progressBar;

            @Override
            public void onStart() {
                super.onStart();
                try {
                    progressBar = new ProgressDialog(getActivity());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Loading ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressBar.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (statusCode == 200) {
                    try {
                        JSONObject jObject = new JSONObject(responseString);
                        String success = CommonUtils.getValidString(jObject.getString("success"));
                        if ("1".equals(success)) {
                            JSONArray jarrData = new JSONArray(jObject.getString("data"));
                            for (int i = 0; i < jarrData.length(); i++) {
                                News news = News.getNews(jarrData.getJSONObject(i));
                                mainActivity.newsArrayList.add(news);
                            }

                        } else {
                            String message = CommonUtils.getValidString(jObject.getString("message"));
                            CommonUtils.showOkDialog(getActivity(), getResources().getString(R.string.dialog_title_common), message, null);
                        }
                    } catch (Exception e) {
                        CommonUtils.showOkDialog(getActivity(), getResources().getString(R.string.dialog_title_common), e.getMessage(), null);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                CommonUtils.showOkDialog(getActivity(), getResources().getString(R.string.dialog_title_common), statusCode + "\n" + responseString, null);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                newsArrayAdapter.notifyDataSetChanged();
                progressBar.dismiss();
            }
        });

    }
}
