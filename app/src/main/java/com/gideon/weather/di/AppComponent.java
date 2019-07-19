package com.gideon.weather.di;

import com.gideon.weather.base.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/*The root of out dagger dependency map*/
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
        }
)
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App application);
        AppComponent build();
    }
}
