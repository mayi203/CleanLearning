package com.mayi.presentation.presenter;

import com.mayi.presentation.view.IArticleView;

/**
 * @author: liwenfei.
 * data: 2018/7/14 14:22.
 */
public interface IGetArticlePresenter {
    void setView(IArticleView view);
    void getArticle();
    void getLastArticle();
    void getNextArticle();
}
