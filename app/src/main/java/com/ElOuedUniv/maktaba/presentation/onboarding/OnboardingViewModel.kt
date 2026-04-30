package com.ElOuedUniv.maktaba.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.OnboardingPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingPreferences: OnboardingPreferences
) : ViewModel() {

    val hasCompletedOnboarding = onboardingPreferences.hasCompletedOnboarding

    fun setOnboardingCompleted() {
        viewModelScope.launch {
            onboardingPreferences.setOnboardingCompleted()
        }
    }
}