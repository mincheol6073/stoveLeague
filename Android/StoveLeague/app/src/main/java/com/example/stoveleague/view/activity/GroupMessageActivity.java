//package com.example.stoveleague.view.activity;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.example.stoveleague.R;
//import com.example.stoveleague.data.ChatRoomModel;
//import com.example.stoveleague.data.UserModel;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class GroupMessageActivity extends AppCompatActivity {
//    private SharedPreferences prefs;
//    Map<String, UserModel> users = new HashMap<>();
//    @Override
//    public void onBackPressed() {
//    }
//
//    private String destinationRoom;
//    private Button button;
//    private EditText editText;
//    private RecyclerView recyclerView;
//    private String uid;
//    private UserModel destinationUserModel;
//    List<ChatRoomModel.Comment> comments = new ArrayList<>();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_message);
//        destinationRoom = getIntent().getStringExtra("destinationRoom"); //채팅당하는 아이디
//        prefs = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
//        uid = prefs.getString("encode_email", "'"); //채팅요구아이디
//        button = (Button) findViewById(R.id.messageActivity_button);
//        editText = (EditText)findViewById(R.id.groupMessageActivity_editText);
//
//        recyclerView = (RecyclerView) findViewById(R.id.messageActivity_recyclerview);
//
//        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot item : dataSnapshot.getChildren()) {
//                    ChatRoomModel chatModel = item.getValue(ChatRoomModel.class);
//                    if (chatModel.getUsers().containsKey(destinationUid)) {
//                        chatRoomUid = item.getKey();
//                        recyclerView.setLayoutManager(new LinearLayoutManager(GroupMessageActivity.this));
//                        recyclerView.setAdapter(new GroupMessageActivity.RecyclerViewAdapter());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ChatRoomModel chatModel = new ChatRoomModel();
//
//                chatModel.getUsers().put(uid, true);
//                chatModel.getUsers().put(destinationUid, true);
//                if (chatRoomUid == null) {
//                    FirebaseDatabase.getInstance().getReference().child("chatrooms").push().setValue(chatModel);
//                } else {
//                    ChatRoomModel.Comment comment = new ChatRoomModel.Comment();
//                    comment.uid = uid;
//                    comment.message = editText.getText().toString();
//                    FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments").push().setValue(comment);
//                }
//            }
//        });
//    }
//
//    void checkChatRoom() {
//        FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/" + uid).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot item : dataSnapshot.getChildren()) {
//                    ChatRoomModel chatModel = item.getValue(ChatRoomModel.class);
//                    if (chatModel.getUsers().containsKey(destinationUid)) {
//                        chatRoomUid = item.getKey();
//                        recyclerView.setLayoutManager(new LinearLayoutManager(GroupMessageActivity.this));
//                        recyclerView.setAdapter(new GroupMessageActivity.RecyclerViewAdapter());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//    class GroupMessageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
//        List<ChatRoomModel.Comment> comments;
//
//        public GroupMessageRecyclerViewAdapter(){
//
//        }
//
//        @NonNull
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
//            return new GroupMessageActivity.RecyclerViewAdapter.CustomViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//            ((GroupMessageActivity.RecyclerViewAdapter.CustomViewHolder) holder).textview.setText(comments.get(position).message);
//        }
//
//        @Override
//        public int getItemCount() {
//            return comments.size();
//        }
//        private class CustomViewHolder extends RecyclerView.ViewHolder {
//            public TextView textview;
//
//            public CustomViewHolder(View view) {
//                super(view);
//                textview = (TextView) view.findViewById(R.id.messageItem_txtView_message);
//            }
//        }
//
//    }
//}
