package com.unbelievable.uetsupport.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.unbelievable.uetsupport.LoginActivity;
import com.unbelievable.uetsupport.R;

/**
 * Created by DucAnhZ on 22/11/2015.
 */
public class NoLoginFragment extends Fragment {
    private View view;
    private Button btnToLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_not_login, container, false);
        btnToLogin = (Button) view.findViewById(R.id.btnLogin);
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
