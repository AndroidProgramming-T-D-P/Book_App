package com.example.bookapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DanhGiaModel {

    @SerializedName("success") // Trạng thái trả về từ API
    private boolean success;

    @SerializedName("message") // Thông báo từ API
    private String message;

    @SerializedName("result") // Danh sách sách
    private List<DanhGia> result;

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

    public List<DanhGia> getResult() {
        return result;
    }

    public void setResult(List<DanhGia> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DanhGiaModel{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
