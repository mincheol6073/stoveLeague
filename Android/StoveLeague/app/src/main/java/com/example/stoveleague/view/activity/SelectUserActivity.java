package com.example.stoveleague.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stoveleague.R;
import com.example.stoveleague.data.BaseResponse;
import com.example.stoveleague.data.ChatRoomModel;
import com.example.stoveleague.data.UserModel;
import com.example.stoveleague.util.RetrofiConnection;
import com.example.stoveleague.util.RetrofitInterface;
import com.example.stoveleague.util.StrUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SelectUserActivity extends AppCompatActivity {
    private ChatRoomModel chatModel = new ChatRoomModel();
    private Button mbtnCreateChatRoom;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        mbtnCreateChatRoom = (Button) findViewById(R.id.btnCreateChatRoom);
        RecyclerView recyclerView = findViewById(R.id.recycleView_selectuser);
        recyclerView.setAdapter(new SelectUserRecyclerViewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mbtnCreateChatRoom = (Button) findViewById(R.id.btnCreateChatRoom);
        prefs = getApplicationContext().getSharedPreferences("user",MODE_PRIVATE);
        mbtnCreateChatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = prefs.getString("encode_email","");
                chatModel.getUsers().put(uid,true);
                FirebaseDatabase.getInstance().getReference().child("chatrooms").push().setValue(chatModel);
            }
        });
    }

    class SelectUserRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        ArrayList<UserModel> users;
        public SelectUserRecyclerViewAdapter() {
            users = new ArrayList<>();
            FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    users.clear();
                    String uid = prefs.getString("encode_email","");
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        UserModel addUser = item.getValue(UserModel.class);
                        if(!addUser.getUserId().equals(uid)){
                            users.add(new UserModel(StrUtils.DecodeString(addUser.getUserProfile()), StrUtils.DecodeString(addUser.getUserId()), addUser.getUserName()));
                        }
                    }
                    notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((CustomViewHolder) holder).textview.setText(users.get(position).getUserName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MessageActivity.class);
                    intent.putExtra("destinationUid", users.get(position).getUserId());
                }
            });
            ((CustomViewHolder) holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        chatModel.getUsers().put(StrUtils.EncodeString(users.get(position).getUserId()),true);
                    } else {
                       chatModel.getUsers().remove(StrUtils.EncodeString(users.get(position).getUserId()));
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textview;
            public CheckBox checkBox;

            public CustomViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.imgview_user);
                textview = (TextView) view.findViewById(R.id.txtview_username);
                checkBox = (CheckBox) view.findViewById(R.id.checkbox_user);
            }
        }
    }
}
