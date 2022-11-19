package com.tarikkeskin.simplifiedinstagramstoryplayer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.tarikkeskin.simplifiedinstagramstoryplayer.common.adapters.UserAdapter
import com.tarikkeskin.simplifiedinstagramstoryplayer.common.anim.CubicTransition
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.StoryList
import com.tarikkeskin.simplifiedinstagramstoryplayer.databinding.ActivityMainBinding
import com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.story.StoryFragmentViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var userViewPager: ViewPager2? = null

    private val viewModel: StoryFragmentViewModel by viewModels()

    private val userViewPagerCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewPager(binding)

        viewModel.currentStep.observe(this) { stepFlag ->
            if (stepFlag == 1) { // Go next User
                userViewPager?.currentItem = userViewPager?.currentItem?.plus(1)!!
            } else if (stepFlag == -1) { // Go Previous User
                userViewPager?.currentItem = userViewPager?.currentItem?.minus(1)!!
            }
        }


    }

    private fun setupViewPager(binding: ActivityMainBinding) {
        val adapter = UserAdapter(this, StoryList.user_list)
        userViewPager = binding.viewpagerUser
        userViewPager?.adapter = adapter
        userViewPager?.setPageTransformer(CubicTransition())
        userViewPager?.registerOnPageChangeCallback(userViewPagerCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        userViewPager?.unregisterOnPageChangeCallback(userViewPagerCallback)
    }
}