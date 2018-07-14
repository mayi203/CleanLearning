package practise.mayi.com.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import practise.mayi.com.domain.entity.Key;
import practise.mayi.com.domain.executor.PostExecutionThread;
import practise.mayi.com.domain.executor.ThreadExecutor;
import practise.mayi.com.domain.repository.GetKeyRepository;

/**
 * @author: liwenfei.
 * data: 2018/6/24 22:57.
 */
public class GetKeyUseCase extends UseCase<Key[],Void> {
    private GetKeyRepository getKeyRepository;
    @Inject
    GetKeyUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, GetKeyRepository getKeyRepository){
        super(threadExecutor,postExecutionThread);
        this.getKeyRepository = getKeyRepository;
    }
    @Override
    Observable<Key[]> buildUseCaseObservable(Void aVoid) {
        return getKeyRepository.getKey();
    }
}
