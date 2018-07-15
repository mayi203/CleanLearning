package com.mayi.data.repository;

import com.mayi.data.api.ApiClient;
import com.mayi.data.api.ApiService;
import com.mayi.data.entity.ArticleEntity;

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
            return article;
        });
    }

    @Override
    public Observable<Article> getArticleForDate(String d) {
        return apiService.getArticleForDate(1,d).map(articleEntity -> {
            ArticleEntity.Data data = articleEntity.getData();
            ArticleEntity.Date date = data.getDate();

            Article.Date dateOut = new Article.Date(date.getCurr(),date.getPrev(),date.getNext());
            Article article = new Article(data.getAuthor(),data.getTitle(),data.getDigest(),data.getContent(),data.getWc(),dateOut);
            return article;
        });
    }
}
