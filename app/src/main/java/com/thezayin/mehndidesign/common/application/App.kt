package com.thezayin.mehndidesign.common.application

import android.app.Application
import com.github.anrwatchdog.ANRWatchDog
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.thezayin.di.adModule
import com.thezayin.di.analyticsHelperModule
import com.thezayin.di.appModule
import com.thezayin.di.categoryModule
import com.thezayin.di.databaseModule
import com.thezayin.di.homeModule
import com.thezayin.di.likeModule
import com.thezayin.di.previewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        ANRWatchDog().start()
        ANRWatchDog().setANRListener { error -> // Handle the error. For example, log it to HockeyApp:
            FirebaseCrashlytics.getInstance().log(error.localizedMessage ?: "ANR")
        }.start()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(analyticsHelperModule)
            modules(categoryModule)
            modules(databaseModule)
            modules(previewModule)
            modules(likeModule)
            modules(homeModule)
            modules(appModule)
            modules(adModule)
        }
    }
}