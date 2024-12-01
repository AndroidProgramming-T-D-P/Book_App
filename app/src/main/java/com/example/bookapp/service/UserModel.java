package com.example.bookapp.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel {
    @SerializedName("success")
    boolean success;

    @SerializedName("message")
    String message;

    @SerializedName("result")
    List<User> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }
}
