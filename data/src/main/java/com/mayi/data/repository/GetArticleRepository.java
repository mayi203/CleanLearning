package com.mayi.data.repository;

import android.util.Log;
import com.mayi.data.api.ApiClient;
import com.mayi.data.api.ApiService;
import com.mayi.data.entity.ArticleEntity;
import com.mayi.data.utils.FileUtil;
import com.mayi.data.utils.PathUtil;
import com.mayi.data.utils.RetryWhenProcess;

import java.io.File;
import javax.inject.Inject;
import io.reactivex.Observable;
import practise.mayi.com.domain.entity.Article;
import practise.mayi.com.domain.repository.IGetArticleRepository;

/**
 * @author: liwenfei.
 * data: 2018/7/14 14:10.
 */
public class GetArticleRepository implements IGetArticleRepository {

    private final String TAG = getClass().getSimpleName();
    private ApiService apiService;

    @Inject
    PathUtil pathUtil;

    @Inject
    GetArticleRepository(ApiClient apiClient){
        this.apiService = apiClient.getApiService();
    }

    @Override
    public Observable<Article> getArticle() {
        return apiService.getArticle(1).map(articleEntity -> {
            ArticleEntity.Data data = articleEntity.getData();
            ArticleEntity.Date date = data.getDate();

            Article.Date dateOut = new Article.Date(date.getCurr(),date.getPrev(),date.getNext());
            Article article = new Article(data.getAuthor(),data.getTitle(),data.getDigest(),data.getContent().replaceAll("<p>","<p>&nbsp;&nbsp;&nbsp;&nbsp;"),data.getWc(),dateOut);

            FileUtil.writeObjectToFile(article, pathUtil.getCachePath()+article.getDate().getCurr()+".article");
            return article;
        });
    }

    @Override
    public Observable<Article> getArticleForDate(String d) {
        final Observable<Article> disk = Observable.create(emitter -> {
            File articleFile = new File(pathUtil.getCachePath()+d+".article");
            if(articleFile.exists()){
                emitter.onNext((Article)FileUtil.readObjectFromFile(articleFile.getPath()));
            }else{
                emitter.onComplete();
            }
        });
        final Observable<Article> net = apiService.getArticleForDate(1,d).map(articleEntity -> {
            Log.i(TAG,"current thread: "+ Thread.currentThread().getName());
            ArticleEntity.Data data = articleEntity.getData();
            ArticleEntity.Date date = data.getDate();

            Article.Date dateOut = new Article.Date(date.getCurr(),date.getPrev(),date.getNext());
            Article article = new Article(data.getAuthor(),data.getTitle(),data.getDigest(),data.getContent().replaceAll("<p>","<p>&nbsp;&nbsp;&nbsp;&nbsp;"),data.getWc(),dateOut);

            return article;
        }).retryWhen(new RetryWhenProcess()).doOnNext(article -> {
            Log.i(TAG,"current thread: "+ Thread.currentThread().getName());
            FileUtil.writeObjectToFile(article, pathUtil.getCachePath()+article.getDate().getCurr()+".article");
        });
        return Observable.concat(disk,net);
    }

    @Override
    public Observable<Object> collectArticle(Article article) {
        File articleFile = new File(pathUtil.getCollectionPath()+article.getDate().getCurr()+".article");
        return Observable.create(emitter -> {
            if(!articleFile.exists()){
                FileUtil.writeObjectToFile(article,pathUtil.getCollectionPath()+article.getDate().getCurr()+".article");
                emitter.onComplete();
            }else {
                emitter.onComplete();
            }
        });
    }
}
