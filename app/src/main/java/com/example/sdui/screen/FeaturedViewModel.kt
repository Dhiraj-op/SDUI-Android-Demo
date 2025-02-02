package com.example.sdui.screen

import androidx.lifecycle.ViewModel
import com.example.sdui.FirebaseRealtime
import com.example.sdui.data.FeaturedContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class FeaturedViewModel:ViewModel() {

    private val firebase = FirebaseRealtime()
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState:StateFlow<UiState> = _uiState.asStateFlow()

    init {
        observeContent()  // Start observing Firebase data
    }

    fun retryLoading() {
        observeContent()
    }
    private fun observeContent(){
        firebase.observerFeatureContent(
            onDataChange = { content->
                _uiState.value = UiState.Success(content)
            },
            onError = { error ->
                _uiState.value = error.message?.let { UiState.Error(it) }!!

            }
        )
    }





    sealed class UiState {
        object Loading : UiState()
        data class Success(val content: FeaturedContent) : UiState()
        data class Error(val message: String) : UiState()
    }

}