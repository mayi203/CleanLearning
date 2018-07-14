package practise.mayi.com.domain.interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * @author: liwenfei.
 * data: 2018/6/24 23:32.
 */
public class DefaultObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
