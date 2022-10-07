package com.example.eurotest.ui.screen.articles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.eurotest.R
import com.example.eurotest.ui.StoryUi
import com.example.eurotest.ui.VideoUi
import com.example.eurotest.ui.screen.storydetail.TitleHeadline
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ArticlesScreen(
    viewModel: ArticlesViewModel = hiltViewModel(),
    onVideoClicked: (String) -> Unit,
    onStoryClicked: (Int) -> Unit
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            FeaturedAppBar()
        }
    ) {
        when {
            state.articles.isNotEmpty() -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(state.articles) { article ->
                        when (article) {
                            is StoryUi -> Item(story = article, onStoryClicked = onStoryClicked)
                            is VideoUi -> Item(video = article, onVideoClicked = onVideoClicked)
                        }
                    }
                }
            }
            state.isLoading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(R.string.loading), color = Color.Red)
                    CircularProgressIndicator()
                }
            }
            state.error.isNotEmpty() -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(R.string.loading_error), color = Color.Red)
                }
            }
        }
    }
}

@Composable
fun FeaturedAppBar() {
    TopAppBar(
        title = {
            Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                Text(text = stringResource(R.string.featured))
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
    )
}

@Composable
fun Item(
    modifier: Modifier = Modifier,
    story: StoryUi,
    onStoryClicked: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OverlappingImageTitle(
            imageUrl = story.image,
            sport = story.sport,
            modifier = Modifier.clickable {
                onStoryClicked(story.id)
            })
        Spacer(modifier = Modifier.padding(4.dp))
        TitleHeadline(title = story.title, modifier = Modifier.padding(start = 4.dp))
        Spacer(modifier = Modifier.padding(4.dp))
        AuthorSection(author = story.author, date = story.date)
    }
}

@Composable
fun Item(
    modifier: Modifier = Modifier,
    video: VideoUi,
    onVideoClicked: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OverlappingImageTitle(
            imageUrl = video.thumb,
            sport = video.sport,
            modifier = Modifier.clickable {
                val encodedUrl = URLEncoder.encode(video.url, StandardCharsets.UTF_8.toString())
                onVideoClicked(encodedUrl)
            })
        Spacer(modifier = Modifier.padding(4.dp))
        TitleHeadline(title = video.title, modifier = Modifier.padding(start = 4.dp))
        Spacer(modifier = Modifier.padding(4.dp))
        ViewCountSection(viewCount = video.views.toString())
    }
}


@Composable
fun OverlappingImageTitle(
    modifier: Modifier = Modifier,
    sport: String,
    imageUrl: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = imageUrl)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(1000)
                }).build()
        )
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .align(Alignment.TopStart)
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
        ) {
            TextBackgroundShape(sport)
        }
    }
}

@Composable
fun TextBackgroundShape(title: String) {
    Text(
        text = title.uppercase(),
        style = TextStyle(
            color = Color.White,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .padding(4.dp)
            .background(MaterialTheme.colors.primary, RoundedCornerShape(8.dp))
            .padding(4.dp)
    )
}

@Composable
fun AuthorSection(
    author: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(start = 4.dp)) {
        Row {
            Text(text = "by $author - $date", color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Composable
fun ViewCountSection(
    viewCount: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(start = 4.dp)) {
        Row {
            Text(
                text = "$viewCount views",
                color = Color.Gray, fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
fun VideoPrev() {
    MaterialTheme {
        ArticlesScreen(
            onStoryClicked = {},
            onVideoClicked = {})
    }
}