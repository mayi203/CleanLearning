package practise.mayi.com.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import practise.mayi.com.domain.entity.Article;
import practise.mayi.com.domain.executor.PostExecutionThread;
import practise.mayi.com.domain.executor.ThreadExecutor;
import practise.mayi.com.domain.repository.IGetArticleRepository;

/**
 * @author: liwenfei.
 * data: 2018/7/21 17:45.
 */
public class CollectArticleUserCase extends UserCase<Object,Article>{
    private IGetArticleRepository getArticleRepository;
    @Inject
    CollectArticleUserCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,IGetArticleRepository getArticleRepository) {
        super(threadExecutor, postExecutionThread);
        this.getArticleRepository = getArticleRepository;
    }

    @Override
    Observable<Object> buildUseCaseObservable(Article article) {
        return getArticleRepository.collectArticle(article);
    }
}
