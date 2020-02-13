package com.example.stoveleague.util;

import com.example.stoveleague.data.BaseResponse;
import com.example.stoveleague.data.ChatRoomModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @FormUrlEncoded
    @POST("/api/chat/createRoom")
    Call<ChatRoomModel> createChatRoom(@Field("name") String name);
    @GET("/api/chat/allRoom")
    Call<ChatRoomModel> getAllRoom();
    @FormUrlEncoded
    @POST("/api/user/join")
    Call<BaseResponse> joinUser(@Field("userId")String userId, @Field("userName") String userName);
}
