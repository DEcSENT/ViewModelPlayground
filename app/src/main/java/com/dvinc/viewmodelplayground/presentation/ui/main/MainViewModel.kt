package com.dvinc.viewmodelplayground.presentation.ui.main

import android.arch.lifecycle.MutableLiveData
import com.dvinc.viewmodelplayground.data.repository.friends.FriendsRepository
import com.dvinc.viewmodelplayground.data.repository.profile.ProfileRepository
import com.dvinc.viewmodelplayground.presentation.common.BaseViewModel
import com.dvinc.viewmodelplayground.presentation.model.UserFriend
import com.dvinc.viewmodelplayground.presentation.model.UserProfile
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val profileRepository: ProfileRepository,
        private val friendsRepository: FriendsRepository
) : BaseViewModel() {

    val isUserLoading = MutableLiveData<Boolean>()

    val isFriendsLoading = MutableLiveData<Boolean>()

    val errorData = MutableLiveData<String>()

    val userProfile = MutableLiveData<UserProfile>()

    val userFriends = MutableLiveData<List<UserFriend>>()

    fun loadUserProfile() {
        isUserLoading.value = true
    }

    fun loadUserFriends() {
        isFriendsLoading.value = true
    }
}
