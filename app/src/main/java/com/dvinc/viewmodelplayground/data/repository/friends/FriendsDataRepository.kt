package com.dvinc.viewmodelplayground.data.repository.friends

import com.dvinc.viewmodelplayground.presentation.model.UserFriend
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.collections.ArrayList

class FriendsDataRepository @Inject constructor() : FriendsRepository {

    override fun obtainFriends(): Single<List<UserFriend>> {
        return Single.just(generateMockFriends())
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFriendByName(name: String): UserFriend {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun generateMockFriends(): List<UserFriend> {
        val mockList = ArrayList<UserFriend>()
        (0..10).forEach {
            mockList.add(UserFriend(
                    name = "Friend$it",
                    experience = Random().nextInt(50)
            ))
        }
        return mockList
    }
}
