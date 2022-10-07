package com.example.eurotest.ui.screen.videoplayer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.eurotest.utils.Constants.VIDEO_URL_EXTRA
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uiState = mutableStateOf(PlayerState())
        private set

    init {
        savedStateHandle.get<String>(VIDEO_URL_EXTRA)?.let {
            uiState.value = uiState.value.copy(it)
        }
    }
}

data class PlayerState(
    val link: String? = null,
)