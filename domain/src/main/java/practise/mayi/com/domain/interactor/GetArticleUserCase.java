package practise.mayi.com.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import practise.mayi.com.domain.entity.Article;
import practise.mayi.com.domain.executor.PostExecutionThread;
import practise.mayi.com.domain.executor.ThreadExecutor;
import practise.mayi.com.domain.repository.IGetArticleRepository;

/**
 * @author: liwenfei.
 * data: 2018/7/14 14:07.
 */
public class GetArticleUserCase extends UseCase<Article,Void> {
    private IGetArticleRepository getArticleRepository;

    @Inject
    GetArticleUserCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, IGetArticleRepository getArticleRepository){
        super(executor,postExecutionThread);
        this.getArticleRepository = getArticleRepository;
    }
    @Override
    Observable<Article> buildUseCaseObservable(Void aVoid) {
        return getArticleRepository.getArticle();
    }
}
