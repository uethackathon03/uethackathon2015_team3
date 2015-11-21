package com.unbelievable.uetsupport.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.unbelievable.uetsupport.MainActivity;
import com.unbelievable.uetsupport.R;
import com.unbelievable.uetsupport.adapter.RecruitmentAdapter;
import com.unbelievable.uetsupport.adapter.NewsAdapter;
import com.unbelievable.uetsupport.common.CommonUtils;
import com.unbelievable.uetsupport.common.UETSupportUtils;
import com.unbelievable.uetsupport.objects.News;
import com.unbelievable.uetsupport.objects.Recruitment;
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
    private ArrayList<Recruitment> recruitmentArrayList;
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
        fakeRecruitmentList();
        recruitmentAdapter = new RecruitmentAdapter(getActivity(), recruitmentArrayList);
        newsListView.setAdapter(newsArrayAdapter);
        btNewsSwitch = (Button) v.findViewById(R.id.btNewsSwitch);
        btRecruitmentSwitch = (Button) v.findViewById(R.id.btAnnouceSwitch);
        btNewsSwitch.setOnClickListener(this);
        btRecruitmentSwitch.setOnClickListener(this);
        if (mainActivity.newsArrayList.size() == 0) {
            News news = new News();
            mainActivity.newsArrayList.add(news);
            parseNewsFromServer();
        }
        newsListView.setOnItemClickListener(new NewsOnItemClickListener());

        return v;
    }

    public void fakeRecruitmentList() {
        recruitmentArrayList = new ArrayList<>();
        Recruitment recruitment = new Recruitment("Công ty haivl tuyển sinh viên thực tập", R.mipmap.logo_uet, "Thực tập", 10, "Không lương");
        recruitmentArrayList.add(recruitment);
        recruitment = new Recruitment("Công ty blah blah tuyển nhân viên", R.mipmap.logo_uet, "Web Developer", 10, "5-7 triêu/tháng");
        recruitmentArrayList.add(recruitment);
        recruitment = new Recruitment("Công ty haivl tuyển nhân viên", R.mipmap.user, "Phá hoại", 10, "50k/ngày");
        recruitmentArrayList.add(recruitment);
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

    private void parseNewsFromServer() {
        if (!UETSupportUtils.networkConnected(getActivity())) {
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

    private class NewsOnItemClickListener implements AdapterView.OnItemClickListener {
        private DisplayImageOptions option;

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                view.setEnabled(false);
            }
            option = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.mipmap.ic_launcher)
                    .showImageOnFail(R.mipmap.ic_launcher)
                    .showImageOnLoading(R.mipmap.ic_launcher)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                    .cacheInMemory(true).cacheOnDisk(true).build();
            Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_news);
            dialog.setTitle(mainActivity.newsArrayList.get(position).title);
            ImageView imvDialogPhoto = (ImageView) dialog.findViewById(R.id.imvDialogPhoto);
            TextView tvDialogContent = (TextView) dialog.findViewById(R.id.tvDialogContent);
            try {
                ImageLoader.getInstance().displayImage(mainActivity.newsArrayList.get(position).photo, imvDialogPhoto, option);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tvDialogContent.setText(mainActivity.newsArrayList.get(position).content);
            dialog.show();
        }
    }
}
