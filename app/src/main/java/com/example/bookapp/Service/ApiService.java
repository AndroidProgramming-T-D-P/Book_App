package com.example.bookapp.Service;

import com.example.bookapp.models.DanhGiaModel;
import com.example.bookapp.models.LoaiSachModel;
import com.example.bookapp.models.Photo;
import com.example.bookapp.models.PhotoModel;
import com.example.bookapp.models.SachYeuThichModel;

import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @GET("getloaisach.php")
    Observable<LoaiSachModel> getloaisach();

    @GET("listTimKiemDefault.php")
    Observable<PhotoModel> getsachsearch();

    @POST("getsachtheotheloai.php")
    @FormUrlEncoded
    Observable<PhotoModel> getlistsachtheochude(
            @Field("category_id") int category_id
    );

    @POST("getIdSachByName.php")
    @FormUrlEncoded
    Observable<LoaiSachModel> getidsach(
            @Field("category_name") String category_name
    );

    @GET("getsachmoi.php")
    Observable<PhotoModel> getsachmoi();

    @GET("getsachdecu.php")
    Observable<PhotoModel> getsachdecu();

    @GET("getsachtop10doanhnhan.php")
    Observable<PhotoModel> getach1op10doanhnhan();

    @GET("getsachsuckhoe.php")
    Observable<PhotoModel> getsachsuckhoe();

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

    @POST("getNoiDungSach.php")
    @FormUrlEncoded
    Observable<PhotoModel> getNoiDungSach(
            @Field("book_id") int book_id
    );

    @POST("CheckSachYeuThich.php")
    @FormUrlEncoded
    Observable<SachYeuThichModel> checkSachYeuThich(
            @Field("book_id") int book_id,
            @Field("user_id") int user_id
    );

    @POST("ThemSachVaoMucYeuThich.php")
    @FormUrlEncoded
    Observable<SachYeuThichModel> ThemSachVaoMucYeuThich(
            @Field("book_id") int book_id,
            @Field("user_id") int user_id
    );

    @POST("XoaSachKhoiMucYeuThich.php")
    @FormUrlEncoded
    Observable<SachYeuThichModel> XoaSachKhoiMucMucYeuThich(
            @Field("book_id") int book_id,
            @Field("user_id") int user_id
    );

    @POST("getSachYeuThichTheoIdUser.php")
    @FormUrlEncoded
    Observable<PhotoModel> getSachYeuThich(
            @Field("user_id") int user_id
    );

    @POST("getSachById.php")
    @FormUrlEncoded
    Observable<PhotoModel> getSachById(
            @Field("book_id") int book_id
    );

    @POST("upDateViewer.php")
    @FormUrlEncoded
    Observable<PhotoModel> updateViewer(
            @Field("book_id") int book_id
    );

    @POST("getDanhGia.php")
    @FormUrlEncoded
    Observable<DanhGiaModel> getDanhGia(
            @Field("book_id") int book_id
    );

    @POST("InsertDanhGia.php")
    @FormUrlEncoded
    Observable<DanhGiaModel> ThemDanhGia(
            @Field("book_id") int book_id,
            @Field("user_id") int user_id,
            @Field("rating") int rating,
            @Field("mota") String mota
    );


}