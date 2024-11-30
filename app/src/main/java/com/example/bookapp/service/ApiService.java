package com.example.bookapp.service;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @POST("signup.php")
    @FormUrlEncoded
    Observable<UserModel> dangki(
        @Field("email") String email,
        @Field("userPassWord") String passWord,
        @Field("userName") String userName // Mấy cái String trong Field là phải giống mấy cái tên trường trong database ae nghe
    );
}
