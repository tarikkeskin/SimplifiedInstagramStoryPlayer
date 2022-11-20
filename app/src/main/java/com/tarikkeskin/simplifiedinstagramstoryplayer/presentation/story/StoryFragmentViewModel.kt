package com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StoryFragmentViewModel : ViewModel() {

    private val currentStepMutable = MutableLiveData<Int>()
    val currentStep: LiveData<Int> get() = currentStepMutable

    private val videoDurationMutable = MutableLiveData<Long>()
    val videoDuration: LiveData<Long> get() = videoDurationMutable

    private val toggleVideoMutable = MutableLiveData<Boolean>()
    val toggleVideo: LiveData<Boolean> get() = toggleVideoMutable

    init {
        currentStepMutable.value = 0
    }

    fun setCurrentStep(stepFlag: Int) {
        currentStepMutable.value = stepFlag
    }

    fun setVideoDuration(duration: Long) {
        videoDurationMutable.value = duration
    }
    fun setToggleVideo(toggle:Boolean){
        toggleVideoMutable.value = toggle
    }
}