package com.example.stoveleague.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stoveleague.R;
import com.example.stoveleague.data.ChatRoomModel;
import com.example.stoveleague.data.UserModel;
import com.example.stoveleague.util.RetrofiConnection;
import com.example.stoveleague.util.RetrofitInterface;
import com.example.stoveleague.view.activity.CreateChatActivity;
import com.example.stoveleague.view.activity.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragementChatList extends Fragment {
    private SharedPreferences prefs;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_chatlist,container,false);

        FloatingActionButton mbtnCreate = (FloatingActionButton) view.findViewById(R.id.btn_Create);
        RecyclerView recyclerView = view.findViewById(R.id.recycleView_chatlist);
        recyclerView.setAdapter(new ChatRoomRecyclerViewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

       mbtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateChatActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    class ChatRoomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        List<ChatRoomModel> chatRooms;
        //생성자
        private FirebaseAuth mAuth;
        private RetrofitInterface retroConn;
        private String userID;
        public ChatRoomRecyclerViewAdapter(){
            prefs = getActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
            userID = prefs.getString("email","");
            chatRooms = new ArrayList<>();
//            notifyDataSetChanged();
//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            DatabaseReference myRef = database.getReference("chatrooms");
//            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for(DataSnapshot item : dataSnapshot.getChildren()){
//                        Log.d("!",item.getValue(ChatRoomModel.class).getName()+"//"+item.getValue(ChatRoomModel.class).getRoomId());
//                    }
//                    String value = dataSnapshot.getValue(String.class);
//                    Log.d("!", "Value is: " + value);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chatroom,parent,false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return chatRooms.size();
        }
        private class CustomViewHolder extends RecyclerView.ViewHolder{
            public ImageView imageView;
            public TextView textview;
            public CustomViewHolder(View view){
                super(view);
                imageView = (ImageView) view.findViewById(R.id.imgview_chatroom);
                textview = (TextView) view.findViewById(R.id.txtview_chatroom);
            }
        }
    }
}
