package com.example.eurotest.ui.screen.videoplayer

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerControlView
import com.google.android.exoplayer2.ui.StyledPlayerView


@Composable
fun PlayerScreen(
    vm : PlayerViewModel = hiltViewModel(),
) {
    vm.uiState.value.link?.let {
        VideoPlayer(
            url = it
        )
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
    url : String,
) {

    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

    val systemUi = rememberSystemUiController()
    systemUi.isSystemBarsVisible = false

    val context = LocalContext.current
    val mediaItem = com.google.android.exoplayer2.MediaItem.fromUri(url)

    val exoPlayer = remember (context) {
        val trackSelector = DefaultTrackSelector(
            context, AdaptiveTrackSelection.Factory()
        )
        ExoPlayer.Builder(context).setTrackSelector(trackSelector)
            .build().apply {
                this.setMediaItem(mediaItem)
                this.prepare()
                this.playWhenReady = true
                this.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT


                this.addListener(
                    object: Player.Listener{
                        override fun onPlaybackStateChanged(playbackState: @Player.State Int) {
                            if(playbackState == Player.STATE_IDLE){
                                Log.d("STATE", "Player.STATE_IDLE")
                            }
                            if(playbackState == Player.STATE_BUFFERING){
                                Log.d("STATE", "Player.BUFF")
                            }
                            if(playbackState == Player.STATE_READY){
                                Log.d("STATE", "Player.READY")
                            }
                            if (playbackState == Player.STATE_ENDED) {
                                Log.d("STATE", "Player.ENDED")
                            }
                        }
                    }
                )
            }
    }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val observer = LifecycleEventObserver{ _, event ->
        when(event){
            Lifecycle.Event.ON_START -> {
                exoPlayer.playWhenReady = true
            }
            Lifecycle.Event.ON_PAUSE -> {
                exoPlayer.pause()
            }
            Lifecycle.Event.ON_STOP -> {
                exoPlayer.pause()
            }
            Lifecycle.Event.ON_DESTROY -> {
                exoPlayer.release()
            }
            else -> {

            }
        }

    }

    Box(modifier = modifier.fillMaxSize()){
        DisposableEffect(
            AndroidView(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                , factory = {

                    StyledPlayerControlView(it).apply {
                        // this.
                    }
                    StyledPlayerView(it).apply {
                        layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                        this.useController = true
                        controllerHideOnTouch = true
                        setShowBuffering(StyledPlayerView.SHOW_BUFFERING_WHEN_PLAYING)
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                        setKeepContentOnPlayerReset(true)
                        player = exoPlayer
                    }
                })
        ){
            lifecycle.addObserver(observer)
            onDispose {
                systemUi.isSystemBarsVisible = true
                exoPlayer.release()
                lifecycle.removeObserver(observer)
            }
        }
    }

}

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {  }
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation

        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}


fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

