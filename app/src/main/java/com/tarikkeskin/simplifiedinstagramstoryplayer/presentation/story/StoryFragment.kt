package com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.tarikkeskin.simplifiedinstagramstoryplayer.R
import com.tarikkeskin.simplifiedinstagramstoryplayer.common.StoryAdapter
import com.tarikkeskin.simplifiedinstagramstoryplayer.common.UserAdapter
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.StoryList
import com.tarikkeskin.simplifiedinstagramstoryplayer.databinding.ActivityMainBinding
import com.tarikkeskin.simplifiedinstagramstoryplayer.databinding.FragmentStoryBinding

class StoryFragment : Fragment() {

    private lateinit var binding: FragmentStoryBinding

    private var storyViewPager: ViewPager2? = null

    private val storyViewPagerCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)


        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int,
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_story, container, false)
        setupViewPager(binding)

        binding.cLNext.setOnClickListener {
            storyViewPager?.currentItem = storyViewPager?.currentItem?.plus(1)!!
        }
        binding.cLBack.setOnClickListener {
            storyViewPager?.currentItem = storyViewPager?.currentItem?.minus(1)!!
        }
        return binding.root
    }


    private fun setupViewPager(binding: FragmentStoryBinding) {
        val position = arguments?.getInt(POSITION_ARG)
        storyViewPager = binding.viewPagerStory


        // Disable inner viewpager2 swipe operation!!
        storyViewPager?.isUserInputEnabled = false

        position?.let {
            when (position) {
                0 ->{
                    storyViewPager?.adapter = StoryAdapter(requireContext(), StoryList.story_list_1)
                    storyViewPager?.registerOnPageChangeCallback(storyViewPagerCallback)
                }
                1 ->{
                    storyViewPager?.adapter = StoryAdapter(requireContext(), StoryList.story_list_2)
                    storyViewPager?.registerOnPageChangeCallback(storyViewPagerCallback)
                }
                2 -> {
                    storyViewPager?.adapter = StoryAdapter(requireContext(), StoryList.story_list_3)
                    storyViewPager?.registerOnPageChangeCallback(storyViewPagerCallback)
                }
                3 -> {
                    storyViewPager?.adapter = StoryAdapter(requireContext(), StoryList.story_list_4)
                    storyViewPager?.registerOnPageChangeCallback(storyViewPagerCallback)
                }
                4 -> {
                    storyViewPager?.adapter = StoryAdapter(requireContext(), StoryList.story_list_5)
                    storyViewPager?.registerOnPageChangeCallback(storyViewPagerCallback)
                }
                else -> {}
            }
        }

        TabLayoutMediator(binding.tablayout,
            storyViewPager!!) { tab, position ->
        }.attach()

    }

    override fun onDestroy() {
        super.onDestroy()
        storyViewPager?.unregisterOnPageChangeCallback(storyViewPagerCallback)
    }

    // This is for Singleton Pattern (java static)
    companion object {
        var POSITION_ARG = "position_arg"

        @JvmStatic
        fun newInstance(position: Int) = StoryFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION_ARG, position)
            }
        }
    }


}