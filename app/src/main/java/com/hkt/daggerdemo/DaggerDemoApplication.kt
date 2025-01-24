package com.hkt.daggerdemo

import android.app.Application
import com.hkt.daggerdemo.di.AppComponent
import com.hkt.daggerdemo.di.DaggerAppComponent

class DaggerDemoApplication : Application() {
    lateinit var appComponent: AppComponent
        private set
        
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
} 