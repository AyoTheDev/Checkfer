package com.ayo.doggallery

import com.ayo.api.di.NetworkModule
import com.ayo.doggallery.di.component.DaggerApplicationComponent
import com.ayo.doggallery.di.module.ApplicationModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build()
    }
}