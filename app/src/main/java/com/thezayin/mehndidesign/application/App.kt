package com.thezayin.mehndidesign.application

import android.app.Application
import com.google.firebase.FirebaseApp
import com.thezayin.category.presentation.di.categoriesModule
import com.thezayin.favorites.presentation.di.favoritesModule
import com.thezayin.framework.di.frameworkModule
import com.thezayin.homes.presentation.di.homeModule
import com.thezayin.preview.presentation.di.previewModule
import com.thezayin.start_up.setting.di.settingModule
import com.thezayin.start_up.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(favoritesModule)
            modules(categoriesModule)
            modules(previewModule)
            modules(settingModule)
            modules(splashModule)
            modules(homeModule)
            modules(frameworkModule)
        }
    }
}