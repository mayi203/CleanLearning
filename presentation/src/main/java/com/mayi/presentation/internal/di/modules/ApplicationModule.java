package com.mayi.presentation.internal.di.modules;

import android.app.Application;
import android.content.Context;

import com.mayi.data.executor.JobExecutor;
import com.mayi.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import practise.mayi.com.domain.executor.PostExecutionThread;
import practise.mayi.com.domain.executor.ThreadExecutor;

@Module
public abstract class ApplicationModule {

    @Singleton
    @Binds
    abstract Context provideApplicationContext(Application application);

    @Singleton
    @Binds
    abstract ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor);

    @Singleton
    @Binds
    abstract PostExecutionThread providePostExecutionThread(UIThread uiThread);


}
