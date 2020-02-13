package com.example.stoveleague.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.stoveleague.R;
import com.example.stoveleague.view.activity.CreateChatActivity;
import com.example.stoveleague.view.activity.MainActivity;

public class FragementAccount extends Fragment {
    SharedPreferences prefs;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_account, container, false);
        prefs = getActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Button mbntLogout = (Button) view.findViewById(R.id.btn_logout);
        TextView mtxtEmail = (TextView) view.findViewById((R.id.txtview_email));
        TextView mtxtName = (TextView) view.findViewById((R.id.txtview_name));
        TextView mtxtProfile = (TextView) view.findViewById((R.id.txtview_profile));

        mtxtEmail.setText(prefs.getString("email",""));
        mtxtName.setText(prefs.getString("name",""));
        mtxtProfile.setText(prefs.getString("profile",""));

        mbntLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.clear();
                editor.commit();
                MainActivity.mOAuthLoginModule.logout(getContext());
                getActivity().finish();
            }
        });
        return view;
    }
}
