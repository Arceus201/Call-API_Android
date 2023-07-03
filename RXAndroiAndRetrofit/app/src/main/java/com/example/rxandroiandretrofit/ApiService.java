package com.example.rxandroiandretrofit;

import java.util.List;
import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {
    //------------------log request response---------------
    HttpLoggingInterceptor logHttpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                                        .readTimeout(30, TimeUnit.SECONDS)
                                        .connectTimeout(30,TimeUnit.SECONDS)
                                        .retryOnConnectionFailure(true) // co muon thu lai khi ket noi bi loi khong
                                        .addInterceptor(logHttpLoggingInterceptor);

    //https://jsonplaceholder.typicode.com/posts

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okBuilder.build())
            .build()
            .create(ApiService.class);

    @GET("/posts")
    Observable<List<ObjectData>> callApi();



}
