package com.example.eurotest.ui.screen.storydetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eurotest.domain.ArticlesUseCase
import com.example.eurotest.ui.StoryUi
import com.example.eurotest.ui.toUi
import com.example.eurotest.utils.Constants.STORY_ID_EXTRA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val articlesUseCase: ArticlesUseCase
) : ViewModel() {

    var uiState = mutableStateOf(StoryDetailState())
        private set

    init {
        savedStateHandle.get<Int>(STORY_ID_EXTRA)?.let { storyId ->
            getStoryById(storyId)
        }
    }

    private fun getStoryById(storyId: Int) {
        viewModelScope.launch {
            val story = articlesUseCase.getStoryById(storyId)
            uiState.value = uiState.value.copy(story = story.toUi())
        }
    }
}

data class StoryDetailState(
    //TODO(ADD TIME AGO)
    val story: StoryUi? = null,
)