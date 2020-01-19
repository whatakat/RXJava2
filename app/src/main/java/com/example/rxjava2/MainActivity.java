package com.example.rxjava2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> observable = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);

                return "result";
            }
        });
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //do something with "s"

            }
        });
        Observable<String> justObservable = Observable.just("just");
        justObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Timber.d("onSubscribe");

            }

            @Override
            public void onNext(String s) {
                Timber.d("onNext: "+s);

            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e,"failed to from");

            }

            @Override
            public void onComplete() {
                Timber.d("onComplete");

            }
        });
        Observable<String> fromObservable = Observable.fromIterable(Arrays.asList("from0","from1","from2"));
        fromObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        Observable<Long> intervalObservable = Observable.interval(1, TimeUnit.SECONDS);
        intervalObservable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Timber.d("onNext: "+aLong);

            }
        });
    }
}
