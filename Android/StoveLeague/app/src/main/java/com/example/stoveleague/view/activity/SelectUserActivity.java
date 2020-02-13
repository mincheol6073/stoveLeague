package com.example.stoveleague.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stoveleague.R;
import com.example.stoveleague.data.ChatRoomModel;
import com.example.stoveleague.data.UserModel;
import com.example.stoveleague.util.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

public class SelectUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        RecyclerView recyclerView = findViewById(R.id.recycleView_selectuser);
        recyclerView.setAdapter(new SelectUserRecyclerViewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    class SelectUserRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        List<UserModel> users;
        //생성자
        public SelectUserRecyclerViewAdapter(){
            users  = new ArrayList<>();
            users.add(new UserModel("ㅇ","ㅇ","ㅇ"));
            notifyDataSetChanged();
            /*
            // 통신하는 곳
            retroConn =  RetrofiConnection.getClient().create(RetrofitInterface.class);
            Call<ChatRoomModel> call = retroConn.createChatRoom("1234");
            call.enqueue(new Callback<ChatRoomModel>() {
                @Override
                public void onResponse(Call<ChatRoomModel> call, Response<ChatRoomModel> response) {
                    //chatroom clear
                    Log.d("dfsdf",response.body().toString());
                    //여기에 Chatroom넣어주기
                }
                @Override
                public void onFailure(Call<ChatRoomModel> call, Throwable t) {

                }
            });*/

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return users.size();
        }
       private class CustomViewHolder extends RecyclerView.ViewHolder{
            public ImageView imageView;
            public TextView textview;
            public CheckBox checkBox;
            public CustomViewHolder(View view){
            super(view);
                imageView = (ImageView) view.findViewById(R.id.imgview_user);
                textview = (TextView) view.findViewById(R.id.txtview_username);
                checkBox = (CheckBox) view.findViewById(R.id.checkbox_user);
            }
      }
    }
}
