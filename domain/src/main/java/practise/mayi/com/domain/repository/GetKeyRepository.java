package practise.mayi.com.domain.repository;

import io.reactivex.Observable;
import practise.mayi.com.domain.entity.Key;

/**
 * Created by xujiangang on 2018/6/7.
 */

public interface GetKeyRepository {
    Observable<Key[]> getKey();
}
