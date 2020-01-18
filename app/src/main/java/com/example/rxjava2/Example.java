package com.example.rxjava2;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class Example {
    /* create Observable example 1
    Observable<String> todoObservable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            try {
                List<String> str = getStrings();
                for (String string:str){
                    emitter.onNext(string);
                }
                emitter.onComplete();
            }catch (Exception e){
                emitter.onError(e);
            }
        }
    });*/
    public static void main(String[] args) throws IOException {
        Observable<String> observable = Observable.fromArray("a", "b", "c", "d");
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: "+s);

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: "+e);

            }

            @Override
            public void onComplete() {
                System.out.println("onComplited");

            }
        };
        observable.subscribe(observer);
    }

}
