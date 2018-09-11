package com.dvinc.viewmodelplayground.presentation.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dvinc.viewmodelplayground.R
import com.dvinc.viewmodelplayground.presentation.App
import com.dvinc.viewmodelplayground.presentation.common.adapter.FriendItem
import com.dvinc.viewmodelplayground.presentation.common.extension.toggleGone
import com.dvinc.viewmodelplayground.presentation.common.extension.toggleVisible
import com.dvinc.viewmodelplayground.presentation.model.UserFriend
import com.dvinc.viewmodelplayground.presentation.model.UserProfile
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.main_screen_friends_list as friendsRecycler
import kotlinx.android.synthetic.main.activity_main.main_screen_user_group as userInfoGroup
import kotlinx.android.synthetic.main.activity_main.main_screen_load_friends_button as loadButton
import kotlinx.android.synthetic.main.activity_main.main_screen_user_name as userName
import kotlinx.android.synthetic.main.activity_main.main_screen_user_experience_value as userExperience
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.main_screen_user_loader as userLoader
import kotlinx.android.synthetic.main.activity_main.main_screen_friend_list_loader_group as friendsLoader

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mainViewModel: MainViewModel

    private val friendsAdapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).appComponent.inject(this)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

        initObservers(mainViewModel)

        setupLoadButton()
        setupRecycler()
    }

    private fun initObservers(mainViewModel: MainViewModel) {
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
        userInfoGroup.toggleVisible(!isLoading)
    }

    private fun setFriendsLoading(isLoading: Boolean) {
        friendsLoader.toggleGone(isLoading)
        friendsRecycler.toggleVisible(!isLoading)
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun setUserProfile(profile: UserProfile) {
        userName.text = profile.name
        userExperience.text = profile.experience.toString()
    }

    private fun setFriendsList(friends: List<UserFriend>) {
        friendsAdapter.addAll(friends.map {
            FriendItem(it)
        })
    }

    private fun setupLoadButton() {
        loadButton.setOnClickListener {
            mainViewModel.loadUserProfile()
            mainViewModel.loadUserFriends()
        }
    }

    private fun setupRecycler() {
        friendsRecycler.adapter = friendsAdapter
    }
}
