package com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tarikkeskin.simplifiedinstagramstoryplayer.R
import com.tarikkeskin.simplifiedinstagramstoryplayer.common.StoryAdapter
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.StoryList
import com.tarikkeskin.simplifiedinstagramstoryplayer.databinding.FragmentStoryBinding

class StoryFragment : Fragment() {

    private lateinit var binding: FragmentStoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_story, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt(POSITION_ARG)
        val viewPager = binding.viewPagerStory

        position?.let {
            when (position) {
                0 -> viewPager?.adapter = StoryAdapter(requireContext(), StoryList.first_story_list)
                1 -> viewPager?.adapter = StoryAdapter(requireContext(), StoryList.first_story_list)
                2 -> viewPager?.adapter = StoryAdapter(requireContext(), StoryList.first_story_list)
            }
        }

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