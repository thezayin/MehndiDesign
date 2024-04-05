package com.thezayin.mehndidesign.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import com.thezayin.mehndidesign.domain.usecase.GetAllImagesUseCase
import com.thezayin.mehndidesign.domain.usecase.SearchUseCase

class HomeViewModel(
    private val useCase: GetAllImagesUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    val getAllImages =
        useCase()
}