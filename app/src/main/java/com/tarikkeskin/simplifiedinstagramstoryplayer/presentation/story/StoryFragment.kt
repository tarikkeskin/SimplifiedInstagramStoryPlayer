package com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tarikkeskin.simplifiedinstagramstoryplayer.R
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


}