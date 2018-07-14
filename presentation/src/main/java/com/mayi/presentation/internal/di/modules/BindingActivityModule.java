package com.mayi.presentation.internal.di.modules;

import com.mayi.presentation.internal.di.scope.ActivityScoped;
import com.mayi.presentation.view.ArticleActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BindingActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = BlankActivityModule.class)
    abstract ArticleActivity articleActivity();
}
