package com.example.xpmarvel.core

import android.app.Application
import com.example.xpmarvel.core.di.AppComponent
import com.example.xpmarvel.core.di.DaggerAppComponent

open class MainApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}