package com.example.stoveleague.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stoveleague.R;
import com.example.stoveleague.data.ChatRoomModel;
import com.example.stoveleague.data.UserModel;
import com.example.stoveleague.util.StrUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    public void onBackPressed() {
    }

    private String destinationUid;
    private Button button;
    private EditText editText;
    private RecyclerView recyclerView;
    private String uid;
    private String chatRoomUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        destinationUid = getIntent().getStringExtra("destinationUid"); //채팅당하는 아이디
        prefs = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        uid = prefs.getString("email", "'"); //채팅요구아이디
        button = (Button) findViewById(R.id.messageActivity_button);
        recyclerView = (RecyclerView) findViewById(R.id.messageActivity_recyclerview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatRoomModel chatModel = new ChatRoomModel();

                chatModel.getUsers().put(uid, true);
                chatModel.getUsers().put(destinationUid, true);
                if (chatRoomUid == null) {
                    FirebaseDatabase.getInstance().getReference().child("chatrooms").push().setValue(chatModel);
                } else {
                    ChatRoomModel.Comment comment = new ChatRoomModel.Comment();
                    comment.uid = uid;
                    comment.message = editText.getText().toString();
                    FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments").push().setValue(comment);
                }
            }
        });
    }

    void checkChatRoom() {
        FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/" + uid).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    ChatRoomModel chatModel = item.getValue(ChatRoomModel.class);
                    if (chatModel.getUsers().containsKey(destinationUid)) {
                        chatRoomUid = item.getKey();
                        recyclerView.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
                        recyclerView.setAdapter(new RecyclerViewAdapter());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        List<ChatRoomModel.Comment> comments;
        public RecyclerViewAdapter(){
            FirebaseDatabase.getInstance().getReference().child("users").child(destinationUid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        UserModel userModel = item.getValue(UserModel.class);
                        getMessageList();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        void getMessageList(){
            comments = new ArrayList<>();
            FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    comments.clear();
                    for(DataSnapshot item : dataSnapshot.getChildren()){
                        comments.add(item.getValue(ChatRoomModel.Comment.class));
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            ((CustomViewHolder) holder).textview_message.setText(comments.get(position).message);
            if(comments.get(position).uid.equals(uid)){
                ((CustomViewHolder) holder).textview_message.setText(comments.get(position).message);
                ((CustomViewHolder) holder).textview_message.setBackgroundResource(R.drawable.rightbubble);
                ((CustomViewHolder) holder).linerLayout_destination.setVisibility(View.INVISIBLE);
                ((CustomViewHolder) holder).textview_message.setTextSize(25);
            }else{
//                ((CustomViewHolder) holder).textview_message.setText(userModel.userNmae);
                ((CustomViewHolder) holder).linerLayout_destination.setVisibility(View.VISIBLE);
                ((CustomViewHolder) holder).textview_message.setBackgroundResource(R.drawable.leftbubble);
                ((CustomViewHolder) holder).textview_message.setText(comments.get(position).message);
                ((CustomViewHolder) holder).textview_message.setTextSize(25);
            }
        }

        @Override
        public int getItemCount() {
            return comments.size();
        }
        private class CustomViewHolder extends RecyclerView.ViewHolder {
            public TextView textview_message;
            public TextView textView_name;
            public ImageView ImageView_profile;
            public LinearLayout linerLayout_destination;
            public CustomViewHolder(View view) {
                super(view);
                textview_message = (TextView) view.findViewById(R.id.messageItem_txtView_message);
                textView_name = (TextView) view.findViewById(R.id.messageItem_textview_name);
                ImageView_profile =(ImageView) view.findViewById(R.id.messageItem_imageview_profile);
                linerLayout_destination = (LinearLayout) view.findViewById(R.id.messageItem_linearlayout);
            }
        }

    }
}
