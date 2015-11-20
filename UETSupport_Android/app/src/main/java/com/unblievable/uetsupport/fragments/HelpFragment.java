package com.unblievable.uetsupport.fragments;

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

import com.unblievable.uetsupport.R;
import com.unblievable.uetsupport.adapter.ListProfAdapter;
import com.unblievable.uetsupport.objects.Office;
import com.unblievable.uetsupport.objects.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        listAdapter = new com.unblievable.uetsupport.adapter.ExpandableListAdapter(this.getActivity(),listDataHeader,listDataChild);
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
}
