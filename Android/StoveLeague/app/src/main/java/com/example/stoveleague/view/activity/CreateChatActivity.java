package com.example.stoveleague.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.stoveleague.R;
import com.example.stoveleague.data.ChatRoomModel;
import com.example.stoveleague.util.RetrofiConnection;
import com.example.stoveleague.util.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateChatActivity extends AppCompatActivity {
    private Button mbtnCreate;
    private RetrofitInterface retroConn;
    private Button mbtnUserAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat);

        init_listener();

    }
    public void init_listener(){

        //btnCreatRoom
        mbtnCreate = (Button) findViewById(R.id.btn_CreateRoom);
        mbtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retroConn =  RetrofiConnection.getClient().create(RetrofitInterface.class);
                Call<ChatRoomModel> call = retroConn.createChatRoom("1234");
                call.enqueue(new Callback<ChatRoomModel>() {
                    @Override
                    public void onResponse(Call<ChatRoomModel> call, Response<ChatRoomModel> response) {
                        Log.d("dfsdf",response.body().toString());
                    }
                    @Override
                    public void onFailure(Call<ChatRoomModel> call, Throwable t) {

                    }
                });
            }
        });
        mbtnUserAdd = (Button) findViewById(R.id.btn_adduser);
        mbtnUserAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity((new Intent(view.getContext(),SelectUserActivity.class)));
            }
        });
    }
}
