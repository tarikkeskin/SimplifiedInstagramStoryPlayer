package com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.story

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.tarikkeskin.simplifiedinstagramstoryplayer.R
import com.tarikkeskin.simplifiedinstagramstoryplayer.common.adapters.StoryAdapter
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.StoryList
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.User
import com.tarikkeskin.simplifiedinstagramstoryplayer.databinding.FragmentStoryBinding
import com.tarikkeskin.simplifiedinstagramstoryplayer.utils.*

class StoryFragment : Fragment() {

    private lateinit var binding: FragmentStoryBinding

    private var storyViewPager: ViewPager2? = null

    private lateinit var progressBar: SegmentPB

    private lateinit var user: User

    private val viewModel: StoryFragmentViewModel by activityViewModels()


    @SuppressLint("ClickableViewAccessibility")
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


        binding.cLNext.setOnTouchListener { view, motionEvent ->
            touchListenerHelper(view,motionEvent)
            true
        }

        binding.cLBack.setOnTouchListener { view, motionEvent ->
            touchListenerHelper(view,motionEvent)
            true
        }


        progressBar.listener = object : SegmentedProgressBarListener {
            override fun onPage(oldPageIndex: Int, newPageIndex: Int) {}
            override fun onFinished() { // User stories end, go next user
                viewModel.setCurrentStep(1)
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

        storyViewPager?.adapter =
            StoryAdapter(requireContext(), user.storyList,viewModel)

        // ------- Data binding -------
        binding.imageName = user.profilePicture
        binding.userName = user.userName

        progressBar = binding.spb
        progressBar.segmentCount = user.storyList.size
        progressBar.viewPager = storyViewPager
        viewModel.videoDuration.observe(viewLifecycleOwner){
            progressBar.timePerSegmentMs = it
        }

    }

    override fun onPause() {
        super.onPause()
        progressBar.pause()
    }

    override fun onResume() {
        super.onResume()
        progressBar.restartSegment()
    }

    private fun hideStoryDetails(){
        binding.cLProfile.invisible()
        binding.cLIndicator.invisible()
        binding.cLBottomDesign.invisible()
    }

    private fun showStoryDetail(){
            binding.cLProfile.visible()
            binding.cLIndicator.visible()
            binding.cLBottomDesign.visible()
    }

    private fun touchListenerHelper(view: View,motionEvent: MotionEvent){
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                progressBar.pause()
                hideStoryDetails()
            }
            MotionEvent.ACTION_UP -> {
                progressBar.start()

                showStoryDetail()
                /**
                 * Handle click in touch listener
                 * Credit
                 * @-> https://stackoverflow.com/a/65670911/14858924
                 */
                if (motionEvent.eventTime - motionEvent.downTime < 200 && motionEvent.actionMasked == MotionEvent.ACTION_UP) {
                    view.performClick()
                }
                /**
                 * End of credit
                 */
            }
        }
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