package com.mayi.data.utils;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author: liwenfei.
 * data: 2018/7/26 11:24.
 */
public class RetryWhenProcess implements Function<Observable<? extends Throwable>,Observable<?>>{
    private int current = 0;
    private int[] timeDely = new int[]{1,2,3,3,3};
    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) {
        return observable.flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
            if(throwable instanceof UnknownHostException || current >= timeDely.length){
                return Observable.error(throwable);
            }else{
                return Observable.timer(timeDely[current++], TimeUnit.SECONDS);
            }
        });
    }
}
