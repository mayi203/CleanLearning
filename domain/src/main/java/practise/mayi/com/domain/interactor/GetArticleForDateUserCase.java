package practise.mayi.com.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import practise.mayi.com.domain.entity.Article;
import practise.mayi.com.domain.executor.PostExecutionThread;
import practise.mayi.com.domain.executor.ThreadExecutor;
import practise.mayi.com.domain.repository.IGetArticleRepository;

/**
 * @author: liwenfei.
 * data: 2018/7/15 13:15.
 */
public class GetArticleForDateUserCase extends UserCase<Article,String > {

    private IGetArticleRepository getArticleRepository;

    @Inject
    GetArticleForDateUserCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,IGetArticleRepository getArticleRepository){
        super(threadExecutor,postExecutionThread);
        this.getArticleRepository = getArticleRepository;
    }
    @Override
    Observable<Article> buildUseCaseObservable(String s) {
        return getArticleRepository.getArticleForDate(s);
    }
}
