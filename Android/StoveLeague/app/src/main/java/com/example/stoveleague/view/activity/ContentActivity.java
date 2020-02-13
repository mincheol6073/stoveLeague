package com.example.stoveleague.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stoveleague.R;
import com.example.stoveleague.view.fragment.FragementAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContentActivity extends AppCompatActivity {

    private FloatingActionButton mbtnCreateRoom ;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragementAccount fragementAccount = new FragementAccount();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout);
//        init_listener();

    }
    public void init_listener(){

        //btnCreatRoom
        mbtnCreateRoom = (FloatingActionButton) findViewById(R.id.btn_CreateRoom);
        mbtnCreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateChatActivity.class);
                startActivity(intent);
            }
        });
    }
}
