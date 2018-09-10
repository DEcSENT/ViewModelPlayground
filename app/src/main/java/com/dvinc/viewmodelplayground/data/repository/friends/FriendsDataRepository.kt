package com.dvinc.viewmodelplayground.data.repository.friends

import com.dvinc.viewmodelplayground.presentation.model.UserFriend
import io.reactivex.Single
import javax.inject.Inject

class FriendsDataRepository @Inject constructor() : FriendsRepository {

    override fun obtainFriends(): Single<List<UserFriend>> {
        return Single.just(emptyList())
    }

    override fun getFriendByName(name: String): UserFriend {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
