package com.mayi.presentation.view;

import android.os.Bundle;
import android.text.Spanned;
import android.widget.TextView;

import com.mayi.presentation.R;
import com.mayi.presentation.R2;
import com.mayi.presentation.presenter.GetArticlePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class ArticleActivity extends DaggerAppCompatActivity implements IArticleView{

    @BindView(R2.id.tv_content)
    TextView tvContent;

    @Inject
    GetArticlePresenter getArticlePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);
        getArticlePresenter.setView(this);
        getArticlePresenter.getArticle();
    }

    @Override
    public void setArticleTitle(String title) {
        setTitle(title);
    }

    @Override
    public void setAuthor(String author) {
        getSupportActionBar().setSubtitle(author);
    }

    @Override
    public void setContent(Spanned content) {
        tvContent.setText(content);
    }
}
