package com.dvinc.viewmodelplayground.presentation.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dvinc.viewmodelplayground.R
import com.dvinc.viewmodelplayground.presentation.common.extension.toggleGone
import com.dvinc.viewmodelplayground.presentation.model.UserFriend
import com.dvinc.viewmodelplayground.presentation.model.UserProfile
import kotlinx.android.synthetic.main.activity_main.main_screen_user_loader as userLoader
import kotlinx.android.synthetic.main.activity_main.main_screen_friend_list_loader_group as friendsLoader

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        initObservers()

        mainViewModel.loadUserProfile()
        mainViewModel.loadUserFriends()
    }

    private fun initObservers() {
        with(mainViewModel) {

            isUserLoading.observe(this@MainActivity, Observer<Boolean> { isLoading ->
                isLoading?.let(::setUserLoading)
            })

            isFriendsLoading.observe(this@MainActivity, Observer<Boolean> { isLoading ->
                isLoading?.let(::setFriendsLoading)
            })

            errorData.observe(this@MainActivity, Observer { errorMessage ->
                errorMessage?.let(::showError)
            })

            userProfile.observe(this@MainActivity, Observer { userData ->
                userData?.let(::setUserProfile)
            })

            userFriends.observe(this@MainActivity, Observer { friends ->
                friends?.let(::setFriendsList)
            })
        }
    }

    private fun setUserLoading(isLoading: Boolean) {
        userLoader.toggleGone(isLoading)
    }

    private fun setFriendsLoading(isLoading: Boolean) {
        friendsLoader.toggleGone(isLoading)
    }

    private fun showError(message: String) {
        //TODO: SHow error
    }

    private fun setUserProfile(profile: UserProfile) {
        //TODO: Set profile here
    }

    private fun setFriendsList(friends: List<UserFriend>) {
        //TODO: Show list
    }
}
