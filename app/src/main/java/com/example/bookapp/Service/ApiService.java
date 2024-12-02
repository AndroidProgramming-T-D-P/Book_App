package com.example.bookapp.Service;

import com.example.bookapp.models.LoaiSachModel;
import com.example.bookapp.models.Photo;
import com.example.bookapp.models.PhotoModel;

import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("getloaisach.php")
    Observable<LoaiSachModel> getloaisach();

    @GET("getsachmoi.php")
        Observable<PhotoModel> getsachmoi();

    @GET("getsachdecu.php")
    Observable<PhotoModel> getsachdecu();

    @POST("signup.php")
    @FormUrlEncoded
    Observable<UserModel> dangki(
        @Field("email") String email,
        @Field("userPassWord") String passWord,
        @Field("userName") String userName // Mấy cái String trong Field là phải giống mấy cái tên trường trong database ae nghe
    );

    @POST("login.php")
    @FormUrlEncoded
    Observable<UserModel> dangnhap(
            @Field("email") String email,
            @Field("userPassWord") String passWord
            // Mấy cái String trong Field là phải giống mấy cái tên trường trong database ae nghe
    );

}
