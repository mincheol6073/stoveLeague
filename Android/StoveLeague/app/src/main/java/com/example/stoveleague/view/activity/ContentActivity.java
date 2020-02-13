package com.example.stoveleague.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.stoveleague.R;
import com.example.stoveleague.data.Const;
import com.example.stoveleague.data.UserModel;
import com.example.stoveleague.util.StompUtils;
import com.example.stoveleague.view.fragment.FragementAccount;
import com.example.stoveleague.view.fragment.FragementChatList;
import com.example.stoveleague.view.fragment.FragementLog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.WebSocket;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.client.StompClient;

public class ContentActivity extends AppCompatActivity {

    private FloatingActionButton mbtnCreateRoom ;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragementAccount fragementAccount = new FragementAccount();
    private FragementChatList fragementChatList = new FragementChatList();
    private FragementLog fragementLog = new FragementLog();
    private boolean isConenct = false;
    private SharedPreferences prefs;
    private UserModel user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Const.TAG = activity/;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        this.init();
//        this.connectWS();

        //        init_listener();

    }
    protected  void init(){
        prefs = getApplicationContext().getSharedPreferences("user",MODE_PRIVATE);
        Log.d("tagsss",prefs.getString("email","")+ prefs.getString("name","")+prefs.getString("profile",""));
        user = new UserModel(prefs.getString("email",""),prefs.getString("name",""),prefs.getString("profile",""));
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout,fragementAccount).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    protected void init_listener(){

    }

    class ItemSelectedListener implements  BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch(item.getItemId()){
                    case R.id.action_account:
                        transaction.replace(R.id.frameLayout,fragementAccount).commitAllowingStateLoss();
                        break;
                    case R.id.action_chatroom:
                        transaction.replace(R.id.frameLayout,fragementChatList).commitAllowingStateLoss();
                        break;
                    case R.id.action_log:
                        transaction.replace(R.id.frameLayout,fragementLog).commitAllowingStateLoss();
                        break;
                }
            return true;
        }
    }
}
