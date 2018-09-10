package com.dvinc.viewmodelplayground.data.repository.profile

import com.dvinc.viewmodelplayground.presentation.model.UserProfile
import io.reactivex.Single

interface ProfileRepository {

    fun obtainProfile(): Single<UserProfile>
}
