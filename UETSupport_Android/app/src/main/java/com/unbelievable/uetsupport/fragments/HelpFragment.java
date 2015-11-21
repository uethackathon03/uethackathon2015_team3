package com.unbelievable.uetsupport.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.loopj.android.http.TextHttpResponseHandler;
import com.unbelievable.uetsupport.R;
import com.unbelievable.uetsupport.adapter.ListProfAdapter;
import com.unbelievable.uetsupport.common.CommonUtils;
import com.unbelievable.uetsupport.common.UETSupportUtils;
import com.unbelievable.uetsupport.objects.News;
import com.unbelievable.uetsupport.objects.Office;
import com.unbelievable.uetsupport.objects.QuestionAnswer;
import com.unbelievable.uetsupport.objects.Teacher;
import com.unbelievable.uetsupport.service.CustomAsyncHttpClient;
import com.unbelievable.uetsupport.service.Service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Nam on 11/20/2015.
 */
public class HelpFragment extends Fragment implements View.OnClickListener{
    RadioButton btnProf,btnOff,btnQA;
    ListView listProf,listQA;
    ListProfAdapter profAdapter;
    ArrayList<Teacher> profs;
    ArrayList<Office> offs;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_help, container, false);
        expListView = (ExpandableListView) v.findViewById(R.id.listQA);

        // preparing list data
        prepareListData();
        listAdapter = new com.unbelievable.uetsupport.adapter.ExpandableListAdapter(this.getActivity(),listDataHeader,listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        btnProf = (RadioButton) v.findViewById(R.id.btnProfList);
        btnOff = (RadioButton) v.findViewById(R.id.btnOffiList);
        btnQA = (RadioButton) v.findViewById(R.id.btnQA);
        listProf = (ListView) v.findViewById(R.id.listProf);
        listQA = (ListView) v.findViewById(R.id.listQA);
        profs = new ArrayList<>();
        offs = new ArrayList<>();
        btnProf.setOnClickListener(this);
        btnOff.setOnClickListener(this);
        btnQA.setOnClickListener(this);
        for (int i = 0;i < 10;i++){
            profs.add( new Teacher("Mr.X","0138573730"));
            offs.add(new Office("Văn phòng khoa X","1638914729"));
        }
        profAdapter = new ListProfAdapter(getActivity(),profs,offs,0);
        listProf.setAdapter(profAdapter);
        return v;
    }
    public void prepareListData(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
    @Override
    public void onClick(View v) {
        if (v == btnProf){
            listProf.setVisibility(View.VISIBLE);
            listQA.setVisibility(View.GONE);
            profAdapter = new ListProfAdapter(getActivity(),profs,offs,0);
            listProf.setAdapter(profAdapter);
        }if (v == btnOff){
            listProf.setVisibility(View.VISIBLE);
            listQA.setVisibility(View.GONE);
            profAdapter = new ListProfAdapter(getActivity(),profs,offs,1);
            listProf.setAdapter(profAdapter);
        }if (v == btnQA){
            listProf.setVisibility(View.GONE);
            listQA.setVisibility(View.VISIBLE);
        }
    }


//    //TODO
//    private void parseNewsFromServer() {
//        if(!UETSupportUtils.networkConnected(getActivity())) {
//            return;
//        }
//
//        CustomAsyncHttpClient client = new CustomAsyncHttpClient(getActivity(), "");
//        String url = Service.ServerURL + "/data/question-answers";
//        client.get(url, new TextHttpResponseHandler() {
//            private ProgressDialog progressBar;
//
//            @Override
//            public void onStart() {
//                super.onStart();
//                try {
//                    progressBar = new ProgressDialog(getActivity());
//                    progressBar.setCancelable(true);
//                    progressBar.setMessage("Loading ...");
//                    progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                    progressBar.show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                if (statusCode == 200) {
//                    try {
//                        JSONObject jObject = new JSONObject(responseString);
//                        String success = CommonUtils.getValidString(jObject.getString("success"));
//                        if ("1".equals(success)) {
//                            JSONArray jarrData = new JSONArray(jObject.getString("data"));
//                            for (int i = 0; i < jarrData.length(); i++) {
//                                QuestionAnswer questionAnswer = QuestionAnswer.getQuestionAnswer(jarrData.getJSONObject(i));
//                                newsArrayList.add(news);
//                            }
//
//                        } else {
//                            String message = CommonUtils.getValidString(jObject.getString("message"));
//                            CommonUtils.showOkDialog(getActivity(), getResources().getString(R.string.dialog_title_common), message, null);
//                        }
//                    } catch (Exception e) {
//                        CommonUtils.showOkDialog(getActivity(), getResources().getString(R.string.dialog_title_common), e.getMessage(), null);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                CommonUtils.showOkDialog(getActivity(), getResources().getString(R.string.dialog_title_common), statusCode + "\n" + responseString, null);
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                newsArrayAdapter.notifyDataSetChanged();
//                progressBar.dismiss();
//            }
//        });
//
//    }
}
