package com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.tarikkeskin.simplifiedinstagramstoryplayer.R
import com.tarikkeskin.simplifiedinstagramstoryplayer.common.adapters.StoryAdapter
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.StoryList
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.User
import com.tarikkeskin.simplifiedinstagramstoryplayer.databinding.FragmentStoryBinding

class StoryFragment : Fragment() {

    private lateinit var binding: FragmentStoryBinding

    private var storyViewPager: ViewPager2? = null

    private lateinit var user: User

    private val viewModel: StoryFragmentViewModel by activityViewModels()


    private val storyViewPagerCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

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
            if (storyViewPager?.currentItem == user.storyList.size - 1) {
                viewModel.setCurrentStep(1)
            } else {
                storyViewPager?.currentItem = storyViewPager?.currentItem?.plus(1)!!
            }
        }
        binding.cLBack.setOnClickListener {
            if (storyViewPager?.currentItem == 0) {
                viewModel.setCurrentStep(-1)
            } else {
                storyViewPager?.currentItem = storyViewPager?.currentItem?.minus(1)!!
            }
        }
        return binding.root
    }


    private fun setupViewPager(binding: FragmentStoryBinding) {
        val position = arguments?.getInt(POSITION_ARG)
        storyViewPager = binding.viewPagerStory
        user = StoryList.user_list[position!!]

        // Disable inner viewpager2 swipe operation!!
        storyViewPager?.isUserInputEnabled = false

        //binding.leftIcon.setImageResource(mContext.resources.getIdentifier(option.rowImageName, "drawable",mContext.packageName))
        storyViewPager?.adapter =
            StoryAdapter(requireContext(), user.storyList)
        storyViewPager?.registerOnPageChangeCallback(storyViewPagerCallback)

        // ------- Data binding -------
        binding.imageName = user.profilePicture
        binding.userName = user.userName

        TabLayoutMediator(binding.tablayout,
            storyViewPager!!) { tab, position ->
        }.attach()

    }

    override fun onDestroy() {
        super.onDestroy()
        storyViewPager?.unregisterOnPageChangeCallback(storyViewPagerCallback)
    }

    // Singleton
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