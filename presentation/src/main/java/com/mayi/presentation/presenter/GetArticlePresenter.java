package com.mayi.presentation.presenter;

import android.text.Html;
import android.util.Log;

import com.mayi.presentation.view.IArticleView;

import javax.inject.Inject;

import practise.mayi.com.domain.entity.Article;
import practise.mayi.com.domain.interactor.DefaultObserver;
import practise.mayi.com.domain.interactor.GetArticleUserCase;

/**
 * @author: liwenfei.
 * data: 2018/7/14 14:24.
 */
public class GetArticlePresenter implements IGetArticlePresenter {
    private final String TAG = this.getClass().getSimpleName();
    private GetArticleUserCase getArticleUserCase;
    private IArticleView articleView;

    @Inject
    GetArticlePresenter(GetArticleUserCase getArticleUserCase){
        this.getArticleUserCase = getArticleUserCase;
    }

    @Override
    public void setView(IArticleView view) {
        articleView = view;
    }

    @Override
    public void getArticle() {
        getArticleUserCase.execute(new GetArticleObserver(),null);
    }

    private class GetArticleObserver extends DefaultObserver<Article> {
        @Override
        public void onNext(Article article) {
            super.onNext(article);
            articleView.setArticleTitle(article.getTitle());
            articleView.setAuthor(article.getAuthor());
            articleView.setContent(Html.fromHtml(article.getContent()));
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
