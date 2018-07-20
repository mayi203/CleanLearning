package com.mayi.data.repository;

import com.mayi.data.api.ApiClient;
import com.mayi.data.api.ApiService;
import com.mayi.data.entity.ArticleEntity;
import com.mayi.data.utils.FileUtil;
import com.mayi.data.utils.PathUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Observable;
import practise.mayi.com.domain.entity.Article;
import practise.mayi.com.domain.repository.IGetArticleRepository;

/**
 * @author: liwenfei.
 * data: 2018/7/14 14:10.
 */
public class GetArticleRepository implements IGetArticleRepository {

    private ApiService apiService;

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
            Article article = new Article(data.getAuthor(),data.getTitle(),data.getDigest(),data.getContent(),data.getWc(),dateOut);

            FileUtil.writeObjectToFile(article, PathUtil.getInstance().getArticlePath()+ File.separator+article.getDate().getCurr()+".article");
            return article;
        });
    }

    @Override
    public Observable<Article> getArticleForDate(String d) {
        File articleFile = new File(PathUtil.getInstance().getArticlePath()+File.separator+d+".article");
        if(articleFile.exists()){
            return Observable.create(emitter -> {
                emitter.onNext((Article) FileUtil.readObjectFromFile(articleFile.getPath()));
            });
        }else{
            return apiService.getArticleForDate(1,d).map(articleEntity -> {
                ArticleEntity.Data data = articleEntity.getData();
                ArticleEntity.Date date = data.getDate();

                Article.Date dateOut = new Article.Date(date.getCurr(),date.getPrev(),date.getNext());
                Article article = new Article(data.getAuthor(),data.getTitle(),data.getDigest(),data.getContent(),data.getWc(),dateOut);

                FileUtil.writeObjectToFile(article, PathUtil.getInstance().getArticlePath()+File.separator+article.getDate().getCurr()+".article");
                return article;
            });
        }
    }
}
