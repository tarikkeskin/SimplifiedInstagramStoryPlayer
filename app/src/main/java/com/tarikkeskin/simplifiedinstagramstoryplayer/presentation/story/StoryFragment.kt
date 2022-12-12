package com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.story

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
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


        binding.cLNext.setOnTouchListener { view, motionEvent ->
            touchListenerHelper(view, motionEvent, 1)
            true
        }

        binding.cLBack.setOnTouchListener { view, motionEvent ->
            touchListenerHelper(view, motionEvent, 0)
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
            StoryAdapter(requireContext(), user.storyList, viewModel)

        // ------- Data binding -------
        binding.imageName = user.profilePicture
        binding.userName = user.userName

        progressBar = binding.spb
        progressBar.segmentCount = user.storyList.size
        viewModel.videoDuration.observe(viewLifecycleOwner) {
            progressBar.timePerSegmentMs = it
        }

    }

    override fun onPause() {
        super.onPause()
        progressBar.pause()
    }

    override fun onResume() {
        super.onResume()
        progressBar.viewPager = storyViewPager
        progressBar.restartSegment()
        Handler().postDelayed({
            progressBar.start()
        }, 300)

    }


    private fun touchListenerHelper(view: View, motionEvent: MotionEvent, isNext: Int) {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                progressBar.pause()
                viewModel.setToggleVideo(true)
            }
            MotionEvent.ACTION_UP -> {
                progressBar.start()
                viewModel.setToggleVideo(false)
                /**
                 * Handle click in touch listener
                 * Credit
                 * @-> https://stackoverflow.com/a/65670911/14858924
                 */
                if (motionEvent.eventTime - motionEvent.downTime < 200 && motionEvent.actionMasked == MotionEvent.ACTION_UP) {
                    if (isNext == 1) {
                        if (storyViewPager?.currentItem == user.storyList.size - 1) {
                            viewModel.setCurrentStep(1)
                        } else {
                            storyViewPager?.currentItem = storyViewPager?.currentItem?.plus(1)!!
                        }
                    } else {
                        if (storyViewPager?.currentItem == 0) {
                            viewModel.setCurrentStep(-1)
                        } else {
                            storyViewPager?.currentItem = storyViewPager?.currentItem?.minus(1)!!
                        }
                    }

                }
                /**
                 * End of credit
                 */
            }
        }
    }


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