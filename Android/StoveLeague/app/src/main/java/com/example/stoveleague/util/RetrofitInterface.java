package com.example.stoveleague.util;

import com.example.stoveleague.data.BaseResponse;
import com.example.stoveleague.data.ChatRoomModel;
import com.example.stoveleague.data.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @FormUrlEncoded
    @POST("/api/chat/createRoom")
    Call<ChatRoomModel> createChatRoom(@Field("name") String name);
    @GET("/api/user/findAllUsers")
    Call<List<UserModel>> getAllRoom();
    @FormUrlEncoded
    @POST("/api/user/join")
    Call<BaseResponse> joinUser(@Field("userId")String userId, @Field("userName") String userName);
}
