package com.mayi.presentation.presenter;

import android.os.Build;
import android.text.Html;
import android.util.Log;

import com.mayi.presentation.view.IArticleView;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import practise.mayi.com.domain.entity.Article;
import practise.mayi.com.domain.interactor.CollectArticleUserCase;
import practise.mayi.com.domain.interactor.DefaultObserver;
import practise.mayi.com.domain.interactor.GetArticleForDateUserCase;

/**
 * @author: liwenfei.
 * data: 2018/7/14 14:24.
 */
public class GetArticlePresenter implements IGetArticlePresenter {
    private final String TAG = this.getClass().getSimpleName();
    private GetArticleForDateUserCase getArticleForDateUserCase;
    private CollectArticleUserCase collectArticleUserCase;
    private IArticleView articleView;
    private Article curArticle;

    @Inject
    GetArticlePresenter(GetArticleForDateUserCase getArticleForDateUserCase,CollectArticleUserCase collectArticleUserCase){
        this.getArticleForDateUserCase = getArticleForDateUserCase;
        this.collectArticleUserCase = collectArticleUserCase;
    }

    @Override
    public void setView(IArticleView view) {
        articleView = view;
    }

    @Override
    public void getArticle() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(new Date());
        getArticleForDateUserCase.execute(new GetArticleObserver(),date);
    }

    @Override
    public void getLastArticle() {
        getArticleForDateUserCase.execute(new GetArticleObserver(),curArticle.getDate().getPrev());
    }

    @Override
    public void getNextArticle() {
        getArticleForDateUserCase.execute(new GetArticleObserver(),curArticle.getDate().getNext());
    }

    @Override
    public void collectArticle() {
        collectArticleUserCase.execute(new DefaultObserver<>(),curArticle);
    }

    @Override
    public void onDestroy() {
        getArticleForDateUserCase.dispose();
    }

    private class GetArticleObserver extends DefaultObserver<Article> {
        @Override
        public void onNext(Article article) {
            super.onNext(article);
            curArticle = article;
            articleView.setArticleTitle(curArticle.getTitle());
            articleView.setAuthor(curArticle.getAuthor());
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                articleView.setContent(Html.fromHtml(curArticle.getContent(),Html.FROM_HTML_MODE_COMPACT));
            }else{
                articleView.setContent(Html.fromHtml(curArticle.getContent()));
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Log.e(TAG,e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
