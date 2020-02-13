package com.example.stoveleague.data;

public class BaseResponse<T> {
    private int resultCode;
    private String resultMessage;
    private T resultObject;
}
