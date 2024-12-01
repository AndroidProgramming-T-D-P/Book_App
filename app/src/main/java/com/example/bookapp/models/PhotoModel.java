package com.example.bookapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoModel {
    @SerializedName("success") // Trạng thái trả về từ API
    private boolean success;

    @SerializedName("message") // Thông báo từ API
    private String message;

    @SerializedName("result") // Danh sách sách
    private List<Photo> result;

    // Getter và Setter
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

    public List<Photo> getResult() {
        return result;
    }

    public void setResult(List<Photo> result) {
        this.result = result;
    }
}
