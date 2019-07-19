package com.gideon.weather.di.viewmodels;

import androidx.lifecycle.ViewModelProvider;

import com.gideon.weather.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

/*BOILERPLATE DAGGER CODE
 *This is a workaround for injecting ViewModels.
 * */
@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);

}
