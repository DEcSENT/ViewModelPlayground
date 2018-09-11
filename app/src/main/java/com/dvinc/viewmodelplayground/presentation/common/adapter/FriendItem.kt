package com.dvinc.viewmodelplayground.presentation.common.adapter

import com.dvinc.viewmodelplayground.R
import com.dvinc.viewmodelplayground.presentation.model.UserFriend
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_friend.item_friend_name as friendName
import kotlinx.android.synthetic.main.item_friend.item_friend_experience_text as friendExperience

class FriendItem(
        private val friend: UserFriend
) : Item() {

    override fun getLayout() = R.layout.item_friend

    override fun bind(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            friendName.text = friend.name
            friendExperience.text = friend.experience.toString()
        }
    }
}
