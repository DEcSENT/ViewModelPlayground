package com.dvinc.viewmodelplayground.data.repository.profile

import com.dvinc.viewmodelplayground.presentation.model.UserProfile
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProfileDataRepository @Inject constructor() : ProfileRepository {

    override fun obtainProfile(): Single<UserProfile> {
        return Single.just(UserProfile.DEFAULT_USER)
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
