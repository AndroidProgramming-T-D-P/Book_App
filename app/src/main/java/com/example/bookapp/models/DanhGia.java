package com.example.bookapp.models;

import java.io.Serializable;
import java.util.Date;

public class DanhGia implements Serializable {
    int book_id;
    int user_id;
    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    int rating;
    String mota;
    Date ngayDanhGia;

    public Date getNgayDanhGia() {
        return ngayDanhGia;
    }

    public void setNgayDanhGia(Date ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
