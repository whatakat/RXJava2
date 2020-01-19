package com.example.rxjava2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;


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
    });
    public static void main(String[] args) throws IOException {
        String str = "4";

        Function<String,Integer> myFuncForMap = new Function<String, Integer>(){
            @Override
            public Integer apply(String s) throws Exception {
                return Integer.parseInt(s)*2;
            }
        };
        Function<Integer,Boolean> myFuncFilter = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer i) throws Exception {
                int res = i%3;
                if (res==0) return true;
                return false;
            }
        };
        Observable<String> observable = Observable.fromArray("1", "2", "3", "4");
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
        try {
            System.out.println(myFuncForMap.apply(str));
        }catch (Exception e){
            System.out.println("catch exception "+e);
        }


    }
*/
}
