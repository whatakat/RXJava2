package com.example.rxjava2;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


public class Example {
    Observable<String> newObservable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            try {
                List<String> str = RxJavaUnitTest.this.getStrings();
                for (String string:str){
                    emitter.onNext(string);
                }
                emitter.onComplete();
            }catch (Exception e){
                emitter.onError(e);
            }
        }
    });
}
