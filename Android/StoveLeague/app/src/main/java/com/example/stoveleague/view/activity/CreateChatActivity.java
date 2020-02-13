package com.example.stoveleague.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stoveleague.R;

public class CreateChatActivity extends AppCompatActivity {
    Button mbtnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat);

        init_listener();

    }
    public void init_listener(){

        //btnCreatRoom
        mbtnCreate = (Button) findViewById(R.id.btn_Create);
        mbtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), CreateChatActivity.class);
//                startActivity(intent);
            }
        });
    }
}
