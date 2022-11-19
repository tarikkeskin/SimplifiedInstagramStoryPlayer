package com.tarikkeskin.simplifiedinstagramstoryplayer.common

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.User
import com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.story.StoryFragment

class UserAdapter(activity: AppCompatActivity, private val userList: ArrayList<User>) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
        //return storyList.size
    }

    override fun createFragment(position: Int) = StoryFragment.newInstance(position)

}