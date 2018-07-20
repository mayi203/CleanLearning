package com.mayi.presentation;

import com.mayi.data.utils.PathUtil;
import com.mayi.presentation.internal.di.components.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class CleanApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        PathUtil.getInstance().init(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }
}
