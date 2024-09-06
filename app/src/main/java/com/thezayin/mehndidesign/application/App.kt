package com.thezayin.mehndidesign.application

import android.app.Application
import com.google.firebase.FirebaseApp
import com.thezayin.di.adModule
import com.thezayin.di.analyticsHelperModule
import com.thezayin.di.categoryModule
import com.thezayin.di.databaseModule
import com.thezayin.di.favouriteModule
import com.thezayin.di.homeModule
import com.thezayin.di.previewModule
import com.thezayin.di.settingModule
import com.thezayin.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(analyticsHelperModule)
            modules(favouriteModule)
            modules(categoryModule)
            modules(databaseModule)
            modules(previewModule)
            modules(settingModule)
            modules(splashModule)
            modules(homeModule)
            modules(adModule)
        }
    }
}