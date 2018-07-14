package com.mayi.data.repository;

import com.mayi.data.api.ApiClient;
import com.mayi.data.api.ApiService;

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
            Article article = new Article();
            article.setAuthor(articleEntity.getData().getAuthor());
            article.setContent(articleEntity.getData().getContent());
            article.setDigest(articleEntity.getData().getDigest());
            article.setTitle(articleEntity.getData().getTitle());
            article.setWc(articleEntity.getData().getWc());
            return article;
        });
    }
}
