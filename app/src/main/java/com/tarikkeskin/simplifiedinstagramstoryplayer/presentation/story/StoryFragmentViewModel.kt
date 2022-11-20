package com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StoryFragmentViewModel : ViewModel() {

    private val currentStepMutable = MutableLiveData<Int>()
    val currentStep: LiveData<Int> get() = currentStepMutable

    init {
        currentStepMutable.value = 0
    }

    fun setCurrentStep(stepFlag: Int) {
        currentStepMutable.value = stepFlag
    }
}