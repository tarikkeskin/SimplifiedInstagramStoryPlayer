package com.tarikkeskin.simplifiedinstagramstoryplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.tarikkeskin.simplifiedinstagramstoryplayer.common.UserAdapter
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.StoryList
import com.tarikkeskin.simplifiedinstagramstoryplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var userViewPager: ViewPager2? = null

    private val mainViewPagerCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            /*if(position == StoryList.first_story_list.size - 1){
                //do something..
            }else{
              // move viewpager page +1
              viewpager2?.currentItem = position +1
            }

            */
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int,
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewPager(binding)

    }

    private fun setupViewPager(binding: ActivityMainBinding) {
        val adapter = UserAdapter(this, StoryList.user_list)
        userViewPager = binding.viewpagerUser
        userViewPager?.adapter = adapter
        userViewPager?.registerOnPageChangeCallback(mainViewPagerCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        userViewPager?.unregisterOnPageChangeCallback(mainViewPagerCallback)
    }
}