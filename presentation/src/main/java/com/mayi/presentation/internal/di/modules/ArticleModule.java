package com.mayi.presentation.internal.di.modules;

import com.mayi.data.repository.GetArticleRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import practise.mayi.com.domain.repository.IGetArticleRepository;

/**
 * Created by xujiangang on 2018/6/7.
 */

@Module
public interface ArticleModule {

    @Singleton
    @Binds
    abstract IGetArticleRepository provideGetArticleRepository(GetArticleRepository getArticleRepository);
}

