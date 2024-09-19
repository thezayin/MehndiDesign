package com.thezayin.mehndidesign.application

import android.app.Application
import com.farimarwat.grizzly.GrizzlyMonitorBuilder
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.thezayin.ads.di.adModule
import com.thezayin.analytics.di.analyticsModule
import com.thezayin.databases.di.databaseModule
import com.thezayin.framework.di.appModule
import com.thezayin.presentation.di.categoryImageModule
import com.thezayin.presentation.di.categoryModule
import com.thezayin.presentation.di.favouriteModule
import com.thezayin.presentation.di.homeModule
import com.thezayin.presentation.di.previewModule
import com.thezayin.setting.di.settingModule
import com.thezayin.splash.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        // Initialize Firebase Crashlytics
        val firebaseCrashlytics = FirebaseCrashlytics.getInstance()

        // Initialize and start GrizzlyMonitor with custom settings
        GrizzlyMonitorBuilder(this)
            .withTicker(200L) // Set ticker interval (1-500ms)
            .withThreshold(3000L) // Set ANR threshold (1000-4500ms)
            .withTitle("App Error") // Set custom crash dialog title
            .withMessage("An error occurred. Please restart.") // Set custom crash dialog message
            .withFirebaseCrashLytics(firebaseCrashlytics) // Integrate with Firebase Crashlytics
            .build()
            .start()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(analyticsModule)
            modules(favouriteModule)
            modules(categoryModule)
            modules(categoryImageModule)
            modules(databaseModule)
            modules(previewModule)
            modules(settingModule)
            modules(splashModule)
            modules(homeModule)
            modules(adModule)
            modules(appModule)
        }
    }
}