package com.gideon.weather.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.gideon.weather.adapters.DailyForecastAdapter;
import com.gideon.weather.api.ApiCallInterface;
import com.gideon.weather.base.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gideon.weather.base.Constants.BASE_URL;
import static com.gideon.weather.base.Constants.SHARED_PREFERENCES_KEY;

@Module
public class AppModule {

    /*Network related modules*/
    @Provides
    @Singleton
    public RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        Retrofit client;
        client = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();

        return client;
    }

    @Singleton
    @Provides
    ApiCallInterface getApi(Retrofit retrofit) {
        return retrofit.create(ApiCallInterface.class);
    }

    /*Shared preferences*/
    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(App app){
        return app.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    /*Adapters*/
    @Provides
    DailyForecastAdapter provideDailyForecastAdapter(){
        return new DailyForecastAdapter();
    }
}
