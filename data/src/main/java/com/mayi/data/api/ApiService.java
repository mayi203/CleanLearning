package com.mayi.data.api;

import com.mayi.data.entity.ArticleEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by xujiangang on 2018/6/7.
 */

public interface ApiService {

    @GET("/article/today")
    Observable<ArticleEntity> getArticle(@Query("dev") int id);
}
