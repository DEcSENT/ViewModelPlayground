package com.dvinc.viewmodelplayground.presentation.di.module

import android.content.Context
import com.dvinc.viewmodelplayground.presentation.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApp(): App = app
}
