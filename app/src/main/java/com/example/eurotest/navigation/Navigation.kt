package com.example.eurotest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.eurotest.utils.Constants.STORY_ID_EXTRA
import com.example.eurotest.utils.Constants.VIDEO_URL_EXTRA
import com.example.eurotest.ui.screen.articles.ArticlesScreen
import com.example.eurotest.ui.screen.storydetail.StoryDetailScreen
import com.example.eurotest.ui.screen.videoplayer.PlayerScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ArticlesScreen.route) {
        composable(
            route = Screen.ArticlesScreen.route
        ) {
            ArticlesScreen(
                onVideoClicked = { videoUrl ->
                    //navigate to url
                    navController.navigate(Screen.VideoPlayerScreen.route + "/$videoUrl")
                },
                onStoryClicked = { storyId ->
                    navController.navigate(Screen.StoryScreen.route + "/$storyId") {
                        //FIXME : for some reason this is not working
                        popUpTo(Screen.ArticlesScreen.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.StoryScreen.route + "/{$STORY_ID_EXTRA}",
            arguments = listOf(
                navArgument(STORY_ID_EXTRA) {
                    type = NavType.IntType
                }
            )
        ) {
            StoryDetailScreen()
        }


        composable(route = Screen.VideoPlayerScreen.route + "/{$VIDEO_URL_EXTRA}",
            arguments = listOf(
                navArgument(VIDEO_URL_EXTRA) {
                    type = NavType.StringType
                }
            )) { PlayerScreen() }
    }
}