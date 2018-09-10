package com.dvinc.viewmodelplayground.presentation.di.component

import com.dvinc.viewmodelplayground.presentation.di.module.AppModule
import com.dvinc.viewmodelplayground.presentation.di.module.ViewModelModule
import com.dvinc.viewmodelplayground.presentation.ui.main.MainActivity
import com.dvinc.viewmodelplayground.presentation.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(target: MainActivity)
}
