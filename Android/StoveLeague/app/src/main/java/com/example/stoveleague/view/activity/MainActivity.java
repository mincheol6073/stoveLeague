package com.example.stoveleague.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static OAuthLogin mOAuthLoginModule;
    private OAuthLoginHandler mOAuthLoginHandler = new NaverLoginHandler(this);
    private OAuthLoginButton mOAuthLoginButton;
    public static SharedPreferences userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                MainActivity.this
                , "cqlQFnXjwlOJXkbCl9_Q"
                , "qt_hekduU_"
                , "스토브리그"
                //,OAUTH_CALLBACK_INTENT
                // SDK 4.1.4 버전부터는 OAUTH_CALLBACK_INTENT변수를 사용하지 않습니다.
        );
        userData = getSharedPreferences("user", MODE_PRIVATE);
        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
//        mOAuthLoginButton.setBgResourceId(R.drawable.img_loginbtn_usercustom);
        mOAuthLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOAuthLoginModule.startOauthLoginActivity(MainActivity.this, mOAuthLoginHandler);
            }
        });

    }



    private static class NaverLoginHandler extends OAuthLoginHandler {
        private MainActivity mainactivity;
        private final WeakReference<MainActivity> mActivity;

        public NaverLoginHandler(MainActivity activity) {
            mainactivity = activity;
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void run(boolean success) {
            MainActivity activity = mActivity.get();
            if (success) {
                String accessToken = mOAuthLoginModule.getAccessToken(activity);
                String refreshToken = mOAuthLoginModule.getRefreshToken(activity);
                long expiresAt = mOAuthLoginModule.getExpiresAt(activity);
                String tokenType = mOAuthLoginModule.getTokenType(activity);
                new RequestAPI().execute(accessToken);
            } else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(activity).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(activity);
                Toast.makeText(activity, "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }

        class RequestAPI extends AsyncTask<String, Void, String> {
            private String result;
            private RetrofitInterface retroConn;
            private boolean isGo = false;
            @Override
            protected String doInBackground(String... strings) {
                String token = strings[0];
                String header = "Bearer " + token;
                try {
                    String apiURL = "https://openapi.naver.com/v1/nid/me";
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("Authorization", header);
                    int responseCode = con.getResponseCode();
                    BufferedReader br;
                    if (responseCode == 200) {
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    } else {
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    }
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = br.readLine()) != null) {
                        response.append(inputLine);
                    }
                    result = response.toString();
                    br.close();
                } catch (Exception e) {
                    Log.d("Error", "Call Function RequestAPI() - " + e.getMessage());
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getString("resultcode").equals("00")) {

                        JSONObject jsonObject = new JSONObject(object.getString("response"));
                        Log.d("jsonObject", jsonObject.toString());

                        SharedPreferences.Editor editor = mainactivity.userData.edit();
                        editor.putString("email", jsonObject.getString("email"));
                        editor.putString("name", jsonObject.getString("name"));
//                        editor.putString("nickname", jsonObject.getString("nickname"));
                        editor.putString("profile", jsonObject.getString("profile_image"));
                        editor.putString("encode_email", StrUtils.EncodeString(jsonObject.getString("email")));
                        editor.putString("encode_name", StrUtils.EncodeString(jsonObject.getString("name")));
                        editor.putString("encode_profile", StrUtils.EncodeString(jsonObject.getString("profile_image")));
                        editor.apply();
                        String uid = StrUtils.EncodeString(jsonObject.getString("email"));
                        UserModel userModel = new UserModel(StrUtils.EncodeString(jsonObject.getString("profile_image")), StrUtils.EncodeString(jsonObject.getString("email")), jsonObject.getString("name"));
                        final ProgressDialog progressDoalog;
                        ProgressBar loadProgress = (ProgressBar) mainactivity.findViewById(R.id.progressBar);
                        loadProgress.setVisibility(View.VISIBLE);

                        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot item : dataSnapshot.getChildren()) {
                                    if (item.getValue(UserModel.class).getUserId().equals(uid)) {
                                        loadProgress.setVisibility(View.GONE);
                                        isGo= true;
                                        return;
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                loadProgress.setVisibility(View.GONE);
                            }
                        });
                        //파이어 배이스 연결 하자
                        if(!isGo){
                            FirebaseDatabase.getInstance().getReference().child("users").child(StrUtils.EncodeString(jsonObject.getString("email"))).setValue(userModel);
                            isGo= true;
                        }
                        if(isGo){
                            loadProgress.setVisibility(View.GONE);
                            Intent intent = new Intent(mainactivity, ContentActivity.class);
                            mainactivity.startActivity(intent);
                        }



                    }
                } catch (Exception e) {
                    Log.d("e", "Call Function RequestAPI() - " + e.getMessage());
                }
            }
        }
    }

}
