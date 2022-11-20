package com.tarikkeskin.simplifiedinstagramstoryplayer.common.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.User
import com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.story.StoryFragment

class UserAdapter(activity: AppCompatActivity, private val userList: ArrayList<User>) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return userList.size
    }

    override fun createFragment(position: Int) = StoryFragment.newInstance(position)

}