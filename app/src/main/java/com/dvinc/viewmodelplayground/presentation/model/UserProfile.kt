package com.dvinc.viewmodelplayground.presentation.model

data class UserProfile(
        val name: String,
        val experience: Int) {

    companion object {
        val DEFAULT_USER = UserProfile("DV", 2)
    }
}
