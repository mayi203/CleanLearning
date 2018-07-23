package com.mayi.presentation.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mayi.presentation.R;
import com.mayi.presentation.R2;
import com.mayi.presentation.presenter.GetArticlePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class ArticleActivity extends DaggerAppCompatActivity implements IArticleView{

    private final String TAG = this.getClass().getSimpleName();
    @BindView(R2.id.tv_content)
    TextView tvContent;
    @BindView(R2.id.sv_container)
    ScrollView svContainer;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @Inject
    GetArticlePresenter getArticlePresenter;

    private Animation switchAnimation;

    private float lastX,lastY;
    private int differ = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);
        getArticlePresenter.setView(this);
        getArticlePresenter.getArticle();

        toolbar.inflateMenu(R.menu.article_menu);

        toolbar.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.collect){
                getArticlePresenter.collectArticle();
                return true;
            }
            return false;
        });
        switchAnimation = AnimationUtils.loadAnimation(this,R.anim.article_switch);
        svContainer.setOnTouchListener((v, event) -> {
            switch (event.getAction() & MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN:
                    lastX = event.getX();
                    lastY = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    float differX = event.getX() - lastX;
                    float differY = event.getY() - lastY;
                    if(Math.abs(differX) > differ && (Math.abs(differX) > Math.abs(differY))){
                        if(differX > 0){
                            getArticlePresenter.getLastArticle();
                        }else{
                            getArticlePresenter.getNextArticle();
                        }
                    }
                    break;
                default:
                    break;
            }
            return super.onTouchEvent(event);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getArticlePresenter.onDestroy();
    }

    @Override
    public void setArticleTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void setAuthor(String author) {
        toolbar.setSubtitle(author);
    }

    @Override
    public void setContent(Spanned content) {
        svContainer.scrollTo(0,0) ;
        tvContent.setText(content);
        tvContent.startAnimation(switchAnimation);
    }
}
