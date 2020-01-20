package com.example.rxjava2;

import java.util.concurrent.RecursiveTask;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus {
    private PublishSubject<Object> subject = PublishSubject.create();

    public void send(Object event){
        subject.onNext(event);
    }
    public Observable<Object> getObservable(){
        return subject;
    }
}
