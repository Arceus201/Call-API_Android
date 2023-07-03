package com.example.rxandroiandretrofit;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private Button bt_call_api;
    private List<ObjectData> mListData;

    private  Disposable mDisposable;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_call_api = findViewById(R.id.call_api);
        mListData = new ArrayList<>();
        progressDialog = new ProgressDialog(this);

        bt_call_api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCallAPI();
            }
        });
    }

    private void onClickCallAPI() {
        progressDialog.show();
        ApiService.apiService.callApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ObjectData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                       mDisposable =d;

                    }

                    @Override
                    public void onNext(@NonNull List<ObjectData> objectData) {
                        mListData = objectData;

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        //setAdapter......
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDisposable!=null){
            mDisposable.dispose();
        }
    }
}