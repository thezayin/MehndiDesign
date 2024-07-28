package com.thezayin.mehndidesign.common.application

import android.app.Application
import com.google.firebase.FirebaseApp
import com.thezayin.di.adModule
import com.thezayin.di.appModule
import com.thezayin.di.categoryModule
import com.thezayin.di.databaseModule
import com.thezayin.di.homeModule
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
            modules(appModule)
            modules(adModule)
            modules(categoryModule)
            modules(databaseModule)
            modules(homeModule)
        }
    }
}