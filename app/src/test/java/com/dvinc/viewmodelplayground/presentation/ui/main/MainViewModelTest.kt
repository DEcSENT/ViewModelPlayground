package com.dvinc.viewmodelplayground.presentation.ui.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.dvinc.viewmodelplayground.data.repository.friends.FriendsRepository
import com.dvinc.viewmodelplayground.data.repository.profile.ProfileRepository
import com.dvinc.viewmodelplayground.presentation.model.UserFriend
import com.dvinc.viewmodelplayground.presentation.model.UserProfile
import io.reactivex.Single
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var profileRepository: ProfileRepository

    @Mock
    private lateinit var friendsRepository: FriendsRepository

    @Mock
    lateinit var userObserver: Observer<UserProfile>

    @Mock
    lateinit var friendsObserver: Observer<List<UserFriend>>

    @Mock
    lateinit var userLoadingObserver: Observer<Boolean>

    @Mock
    lateinit var friendsLoadingObserver: Observer<Boolean>

    @Mock
    lateinit var errorLoadingObserver: Observer<String>

    private lateinit var mainViewModel: MainViewModel

    private val user = UserProfile("Test", 0)

    private val friend = UserFriend("Test1", 10)

    private val friendList = arrayListOf(friend, friend)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(profileRepository, friendsRepository)
    }

    //UserProfile liveData tests
    @Test
    fun loadUserProfile() {
        `when`(profileRepository.obtainProfile()).thenReturn(Single.just(user))

        mainViewModel.loadUserProfile()

        verify(profileRepository, times(1)).obtainProfile()
    }

    @Test
    fun `check user profile liveData interaction`() {
        `when`(profileRepository.obtainProfile()).thenReturn(Single.just(user))

        mainViewModel.userProfile.observeForever(userObserver)
        mainViewModel.loadUserProfile()

        verify(userObserver, times(1)).onChanged(user)
    }

    //UserFriends liveData tests
    @Test
    fun loadUserFriends() {
        `when`(friendsRepository.obtainFriends()).thenReturn(Single.just(friendList))

        mainViewModel.loadUserFriends()

        verify(friendsRepository, times(1)).obtainFriends()
    }

    @Test
    fun `check user friends liveData interaction`() {
        `when`(friendsRepository.obtainFriends()).thenReturn(Single.just(friendList))

        mainViewModel.userFriends.observeForever(friendsObserver)
        mainViewModel.loadUserFriends()

        verify(friendsObserver, times(1)).onChanged(friendList)
    }

    //Loading liveData tests
    @Test
    fun `check user profile loading liveData interaction`() {
        `when`(profileRepository.obtainProfile()).thenReturn(Single.just(user))

        mainViewModel.isUserLoading.observeForever(userLoadingObserver)
        mainViewModel.loadUserProfile()

        verify(userLoadingObserver, times(2)).onChanged(anyBoolean())
    }

    @Test
    fun `check user friends loading liveData interaction`() {
        `when`(friendsRepository.obtainFriends()).thenReturn(Single.just(friendList))

        mainViewModel.isFriendsLoading.observeForever(friendsLoadingObserver)
        mainViewModel.loadUserFriends()

        verify(friendsLoadingObserver, times(2)).onChanged(anyBoolean())
    }

    //Error liveData tests
    @Test
    fun `check user profile error liveData interaction`() {
        `when`(profileRepository.obtainProfile()).thenReturn(Single.error(NullPointerException("help me!")))

        mainViewModel.errorData.observeForever(errorLoadingObserver)
        mainViewModel.loadUserProfile()

        verify(errorLoadingObserver, times(1)).onChanged("help me!")
    }

    @Test
    fun `check user friends error liveData interaction`() {
        `when`(friendsRepository.obtainFriends()).thenReturn(Single.error(NullPointerException("help me!")))

        mainViewModel.errorData.observeForever(errorLoadingObserver)
        mainViewModel.loadUserFriends()

        verify(errorLoadingObserver, times(1)).onChanged("help me!")
    }

    @Test
    fun `should call user loading liveData 2 times when error occurred`() {
        `when`(profileRepository.obtainProfile()).thenReturn(Single.error(NullPointerException("help me!")))

        mainViewModel.isUserLoading.observeForever(userLoadingObserver)
        mainViewModel.loadUserProfile()

        verify(userLoadingObserver, times(2)).onChanged(anyBoolean())
    }

    @Test
    fun `should call friends loading liveData 2 times when error occurred`() {
        `when`(friendsRepository.obtainFriends()).thenReturn(Single.error(NullPointerException("help me!")))

        mainViewModel.isFriendsLoading.observeForever(friendsLoadingObserver)
        mainViewModel.loadUserFriends()

        verify(friendsLoadingObserver, times(2)).onChanged(anyBoolean())
    }
}
