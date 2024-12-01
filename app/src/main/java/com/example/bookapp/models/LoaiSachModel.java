package com.example.bookapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoaiSachModel {
    @SerializedName("success")
    boolean success;

    @SerializedName("message")
    String message;

    @SerializedName("result")
    List<LoaiSach> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<LoaiSach> getResult() {
        return result;
    }

    public void setResult(List<LoaiSach> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
