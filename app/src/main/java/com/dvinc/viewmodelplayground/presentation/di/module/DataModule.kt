package com.dvinc.viewmodelplayground.presentation.di.module

import com.dvinc.viewmodelplayground.data.repository.friends.FriendsDataRepository
import com.dvinc.viewmodelplayground.data.repository.friends.FriendsRepository
import com.dvinc.viewmodelplayground.data.repository.profile.ProfileDataRepository
import com.dvinc.viewmodelplayground.data.repository.profile.ProfileRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun provideFriendsRepo(repo: FriendsDataRepository): FriendsRepository

    @Binds
    abstract fun provideProfileRepos(repo: ProfileDataRepository): ProfileRepository
}
