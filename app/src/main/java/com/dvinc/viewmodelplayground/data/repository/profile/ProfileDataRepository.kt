package com.dvinc.viewmodelplayground.data.repository.profile

import com.dvinc.viewmodelplayground.presentation.model.UserProfile
import io.reactivex.Single
import javax.inject.Inject

class ProfileDataRepository @Inject constructor() : ProfileRepository {

    override fun obtainProfile(): Single<UserProfile> {
        return Single.just(UserProfile.DEFAULT_USER)
    }
}
