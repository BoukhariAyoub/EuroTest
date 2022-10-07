package com.example.eurotest.ui.screen.articles

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eurotest.domain.ArticlesUseCase
import com.example.eurotest.ui.ArticleUi
import com.example.eurotest.ui.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articlesUseCases: ArticlesUseCase
) : ViewModel() {

    private val _articles = mutableStateOf(ArticlesState(isLoading = true))
    val state: State<ArticlesState> = _articles

    init {
        downloadArticles()
        getArticles()
    }

    private fun getArticles() {
        articlesUseCases.getSortedArticles().onEach {
            _articles.value = ArticlesState(articles = it.map { it.toUi() })
        }.launchIn(viewModelScope)
    }

    private fun downloadArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _articles.value = ArticlesState(isLoading = true)
                }
                articlesUseCases.downloadArticles()
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    _articles.value = ArticlesState(error = "error while loading : $ex")
                }
            }
        }
    }
}

data class ArticlesState(
    val isLoading: Boolean = false,
    val error: String = "",
    val articles: List<ArticleUi> = emptyList(),
)


