package com.leavingston.exchangerates

import android.app.Application
import com.leavingston.exchangerates.DI.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

//ეს არის აპლიკაციის დონის კლასი , აქედან ვსტარტავთ ჩვენს დი - კოინს.
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(viewModelModule)
        }
    }
}