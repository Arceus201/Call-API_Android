package com.example.rxandroid;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = new User(1, "name");



        //C1,C2
//        Observable<User> observable = getObserverableUser();
//        Observer<User> observer = getObserverUser();

        //C3:
//        Observable<Serializable> observable = getObserverableUser();
//        Observer<Serializable> observer = getObserverUser();

        //C4,C5:
//        Observable<Long> observable = getObserverableUser();
//        Observer<Long> observer = getObserverUser();

        //C6:
//        Observable<Integer> observable = getObserverableUser();
//        Observer<Integer> observer = getObserverUser();

        //C8
        Observable<String> observable = user.getNameDeferObserveable();
        Observer<String> observer = getObserverUser();

        user.setName("Hung");

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// nhan du lieu tren thang gi
                . subscribe(observer);//ket noi 2 thang
    }

    //C1,C2
//    private Observer<User> getObserverUser(){
//        return new Observer<User>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.e(TAG, "onSubscribe: ");
//                mDisposable = d;
//            }
//
//            @Override
//            public void onNext(@NonNull User user) {
//                Log.e(TAG, "onNext: " + user.toString());
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.e(TAG, "onError");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e(TAG, "onComplete");
//            }
//        };
//    }

//    private Observable<User> getObserverableUser(){
//
//
//        // tao 1 Observable bang cach create
////        List<User> listUser = getListUser();
////        return Observable.create(new ObservableOnSubscribe<User>() {
////            @Override
////            public void subscribe(@NonNull ObservableEmitter<User> emitter) throws Throwable {
////                if(listUser == null || listUser.isEmpty()){//neu loi
////                    if(!emitter.isDisposed()){
////                        emitter.onError(new Exception());
////                    }
////                }
////
////                for (User user : listUser){
////                    if(!emitter.isDisposed()){//chua huy ket noi
////                        emitter.onNext(user);
////                    }
////                }
////                if(!emitter.isDisposed()){// hoan thanh cong viec
////                    emitter.onComplete();
////                }
////            }
////        });
//
//
//        //C2: tao bang Array
//        User user1 = new User(1,"Hungnguyen");
//        User user2 = new User(2,"Hungnguyen2");
//        User[] usersArray = new User[]{user1,user2};
//        return Observable.fromArray(usersArray);
//
//
//        return Observable.just(usersArray);
//    }

    //  C3: Observable.just()
    // Neu truyen list thi bo doi tuong <Serializable> di
    // va object phai dc implement <Serializable>
//    private Observer<Serializable> getObserverUser(){
//        return new Observer<Serializable>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.e(TAG, "onSubscribe: ");
//                mDisposable = d;
//            }
//
//            @Override
//            public void onNext(@NonNull Serializable serializable) {
//                Log.e(TAG, "onNext: " + serializable.toString());
//                if(serializable instanceof  User[]){// kiem tra kieu du lieu
//                    User[] user = (User[]) serializable;
//                }
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.e(TAG, "onError");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e(TAG, "onComplete");
//            }
//        };
//    }

//    private Observable<Serializable> getObserverableUser(){
//        User user1 = new User(1,"Hungnguyen");
//        User user2 = new User(2,"Hungnguyen2");
//        User[] usersArray = new User[]{user1,user2};
//
//
//        return Observable.just(usersArray);
//    }


    //C4:Observable.interval()
//    private Observer<Long> getObserverUser(){
//        return new Observer<Long>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.e(TAG, "onSubscribe: ");
//                mDisposable = d;
//            }
//
//            @Override
//            public void onNext(@NonNull Long serializable) {
//                Log.e(TAG, "onNext: " + serializable);
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.e(TAG, "onError");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e(TAG, "onComplete");
//            }
//        };
//    }
//    private Observable<Long> getObserverableUser(){
//        return Observable.interval(3, 5, TimeUnit.SECONDS);
//    }

    //C5:
//    private Observable<Long> getObserverableUser(){
//        return Observable.timer(3, TimeUnit.SECONDS);
//    }

    //C6:
//    private Observer<Integer> getObserverUser(){
//        return new Observer<Integer>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.e(TAG, "onSubscribe: ");
//                mDisposable = d;
//            }
//
//            @Override
//            public void onNext(@NonNull Integer serializable) {
//                Log.e(TAG, "onNext: " + serializable);
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.e(TAG, "onError");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e(TAG, "onComplete");
//            }
//        };
//    }
//    private Observable<Integer> getObserverableUser(){
//        return Observable.range(1, 10);
//    }

    //C7:
//    private Observable<Integer> getObserverableUser(){
//        return Observable.range(1, 5).repeat();
//    }

    //C8:
    private Observer<String> getObserverUser(){
        return new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e(TAG, "onSubscribe: ");
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull String serializable) {
                Log.e(TAG, "onNext: " + serializable);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        };
    }
    private Observable<Integer> getObserverableUser(){
        return Observable.range(1, 5).repeat();
    }




    private List<User>getListUser (){
        List<User> list = new ArrayList<>();
        list.add(new User(1,"Hungnguyen"));
        list.add(new User(2,"Hungnguyen2"));
        list.add(new User(3,"Hungnguyen3"));
        list.add(new User(4,"Hungnguyen4"));

        return list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDisposable!=null){
            mDisposable.dispose();
        }
    }
}