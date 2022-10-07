package com.example.eurotest.navigation

sealed class Screen(val route: String){
    object ArticlesScreen: Screen("articles")
    object VideoPlayerScreen: Screen("videoPlayer")
    object StoryScreen: Screen("channels")
}
