package com.example.rxjava2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.PublishSubject;
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
        // 1 second
        Observable<Long> intervalObservable = Observable.interval(1, TimeUnit.SECONDS);
        intervalObservable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Timber.d("onNext: "+aLong);

            }
        });
           //skip 2 - it's means skip 0 and 1, start from 2. if it 'observable.take(2)... - conversely all before 2(first two)
        Observable<Long> skipObservable = Observable.interval(1, TimeUnit.SECONDS);
        skipObservable.skip(2).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Timber.d("onNext: "+aLong);

            }
        });
        Observable<String> mapObservable = Observable.fromIterable(Arrays.asList("map0","map1","map2","map3"));
        mapObservable.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s.length();
            }
        })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Timber.d("onNext: "+integer);
                    }
                });
        //distinct returns unique values (for our example : distinct0 and distinct10) don't forget for function +0
        Observable<String> distinctObservable = Observable.fromIterable(Arrays.asList("distinct", "distinct0","distinct1")).
                map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s.contains("0")? s : s+ "0" ;
                    }
                }).distinct();
        distinctObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Timber.d("onNext: "+s);
            }
        });
        //filter
        Observable<String> filterObservable = Observable.fromIterable(Arrays.asList("filter1", "filter10", "filter11","filter20")).
                filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                            return !s.contains("0");
                    }
                });
        filterObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Timber.d("onNext: "+s);
            }
        });
        //merge
        Observable<String> mergeObservable1 = Observable.fromIterable(Arrays.asList("merge0","merge2","merge4"));
        Observable<String> mergeObservable2 = Observable.fromIterable(Arrays.asList("merge1","merge3","merge5"));
        mergeObservable1.mergeWith(mergeObservable2).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Timber.d("onNext: "+s);
            }
        });
        //zip
        Observable<String> zipObservable0 = Observable.just("zip0","zip01");
        Observable<String> zipObservable1 = Observable.just("zip1","zip11");
        Observable<String> zipObservable2 = Observable.just("zip2","zip21");
        Observable<List<String>> zipObservable = Observable.zip(zipObservable0, zipObservable1, zipObservable2, new Function3<String, String, String, List<String>>() {
            @Override
            public List<String> apply(String s, String s2, String s3) throws Exception {
                return Arrays.asList(s,s2,s3);
            }
        });
        zipObservable.subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                Timber.d(strings.get(0)+" "+strings.get(1)+" "+strings.get(2));
            }
        });

        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //something
            }
        });
        subject.onNext("123");
        //create
        Observable<Integer> someObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                try {
                    Integer a = 0;
                    a++;
                    emitter.onNext(a);

                }catch (Exception e){
                    emitter.onError(new RuntimeException());
                    return;
                }
                emitter.onComplete();
            }
        });
        //hot Observable

        ConnectableObservable<Long> interval = Observable.interval(500,TimeUnit.SECONDS).publish();
        interval.connect();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        interval.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {

            }
        });
        // Single (may be network calls, get API for example)

        Single<Integer> single = Single.just(1);
        single.subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });




    }
}
