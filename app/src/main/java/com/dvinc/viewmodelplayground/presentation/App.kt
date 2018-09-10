package com.dvinc.viewmodelplayground.presentation

import android.app.Application
import com.dvinc.viewmodelplayground.presentation.di.component.AppComponent
import com.dvinc.viewmodelplayground.presentation.di.component.DaggerAppComponent
import com.dvinc.viewmodelplayground.presentation.di.module.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = buildDi()
    }

    private fun buildDi() = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
}
