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
        View view = inflater.inflate(R.layout.frame_chatlist, container, false);

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

    class ChatRoomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<ChatRoomModel> chatRooms;
        private List<String> keys = new ArrayList<>();
        //생성자
        private FirebaseAuth mAuth;
        private RetrofitInterface retroConn;
        private String userID;

        public ChatRoomRecyclerViewAdapter() {
            prefs = getActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
            userID = prefs.getString("encode_emails", "");
            chatRooms = new ArrayList<>();
            FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/" + userID).equalTo(true).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    chatRooms.clear();
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        chatRooms.add(item.getValue(ChatRoomModel.class));
                        Log.d("!@",item.getKey());
                        keys.add(item.getKey());
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chatroom, parent, false);


            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            final CustomViewHolder customViewHolder = (CustomViewHolder) holder;
            String destinationUid = null;
            for (String user : chatRooms.get(position).getUsers().keySet()) {
                if(!user.equals((userID))){
                    destinationUid = user;
                }
            }
        }

        @Override
        public int getItemCount() {
            return chatRooms.size();
        }
        private class CustomViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textview;
            public CustomViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.imgview_chatroom);
                textview = (TextView) view.findViewById(R.id.txtview_chatroom);
            }
        }
    }
}
