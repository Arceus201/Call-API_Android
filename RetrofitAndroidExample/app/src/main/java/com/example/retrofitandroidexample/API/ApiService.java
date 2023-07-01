package com.example.retrofitandroidexample.API;

import androidx.annotation.NonNull;

import com.example.retrofitandroidexample.Model.EntriesModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {



    //link API https://api.publicapis.org/entries

    //------------------khoi tao retrofit--------------------
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://api.publicapis.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    //------------------------Method get-------------------
    @GET("entries")
    //neu có param:
    // @Query("access key") String access_key,
    // @Query("curencies") String curencies,
    Call<EntriesModel> getEntry();


//    C1:Chỉ có tham số
//    @GET("group/{id}/users")
//    Call<List<User>> groupList(@Path("id") int groupId);

//    C2: có cả tham số(path) và phần param(query) đăng sau
//    @GET("group/{id}/users") - domain
//    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);

//    C3: truyền cả tham số và map
//    @GET("group/{id}/users")
//    Call<List<User>> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);

//      TH Get ma khong co duong dan part, khong co param
//        @GET("/")
//        Call<EntriesModel> getEntry();



    //----------------------------Post----------------------
//    C1:
//    @POST("users/new")
//    Call<User> createUser(@Body User user);

//    C2: Endcoded:
//    @FormUrlEncoded
//    @POST("user/edit")
//    Call<User> updateUser(@Field("first_name") String first, @Field("last_name") String last);

//    C3: truyền file ảnh
//    @Multipart
//    @PUT("user/photo")
//    Call<User> updateUser(@Part("photo") RequestBody photo, @Part("description") RequestBody description);


    // -------------------Cach truyen header--------------------
    //VD Header la Authorization : shfhishhdfisdhdf

    //C1: truyen Header giong nhu 1 tham so
   // @GET("group/{id}/users") - domain
//    Call<List<User>> groupList(@Header ("Authorization") String header, @Path("id") int groupId, @Query("sort") String sort);


    //C2:
    // @GET("group/{id}/users") - domain
//    @Headers("Authorization:shfhishhdfisdhdf")
//    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);

    //C3: neu dung Header o nhieu noi =>add tong vao luon doi tuong
//    Interceptor interceptor = chain -> {
//        Request response = chain.request();
//        Request.Builder builder = response.newBuilder();
//        builder.addHeader("Key", "Value");
//
//        return chain.proceed(builder.build());
//    };
//    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
//                                      .addInterceptor(interceptor);
//    ApiService apiService1 = new Retrofit.Builder()
//            .baseUrl("https://api.publicapis.org/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(okBuilder.build())
//            .build()
//            .create(ApiService.class);

    // @GET("group/{id}/users") - domain
//    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);


    //------------------log request response---------------
//    HttpLoggingInterceptor logHttpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
//    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
//                                      .addInterceptor(interceptor)
//                                        .addInterceptor(logHttpLoggingInterceptor);

    //--------------TimeOut-----------------------
//        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
//                                        .readTimeout(30, TimeUnit.SECONDS)
//                                        .connectTimeout(30,TimeUnit.SECONDS)
//                                        .retryOnConnectionFailure(true) // co muon thu lai khi ket noi bi loi khong
//                                        .addInterceptor(interceptor)
//                                        .addInterceptor(logHttpLoggingInterceptor);


    //----------------------ADD token header--------------------
}
