package com.dvinc.viewmodelplayground.data.repository.friends

import com.dvinc.viewmodelplayground.presentation.model.UserFriend
import io.reactivex.Single

interface FriendsRepository {

    fun obtainFriends(): Single<List<UserFriend>>

    fun getFriendByName(name: String): UserFriend
}
