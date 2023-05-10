package com.igor.composestudy

import android.app.Application
import com.igor.composestudy.di.dataModule
import com.igor.composestudy.di.domainModule
import com.igor.composestudy.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(presentationModule, dataModule, domainModule)
            )
        }
    }
}