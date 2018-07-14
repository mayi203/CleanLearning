package com.mayi.presentation.internal.di.components;

import android.app.Application;

import com.mayi.presentation.internal.di.modules.ApplicationModule;
import com.mayi.presentation.internal.di.modules.BindingActivityModule;
import com.mayi.presentation.internal.di.modules.ArticleModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        BindingActivityModule.class,
        ArticleModule.class,
        AndroidSupportInjectionModule.class
})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        ApplicationComponent.Builder application(Application application);

        ApplicationComponent build();
    }
}
