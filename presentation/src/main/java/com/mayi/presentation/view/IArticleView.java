package com.mayi.presentation.view;

import android.text.Spanned;

/**
 * @author: liwenfei.
 * data: 2018/7/14 14:20.
 */
public interface IArticleView {
    void setArticleTitle(String title);
    void setAuthor(String author);
    void setContent(Spanned content);
}
