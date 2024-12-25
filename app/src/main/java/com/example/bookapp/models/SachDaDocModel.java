package com.example.bookapp.models;

import com.google.gson.annotations.SerializedName;

public class SachDaDocModel {
    @SerializedName("success")
    boolean success;

    @SerializedName("message")
    String message;

    @SerializedName("result")
    boolean result;

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

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SachYeuThichModel{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
