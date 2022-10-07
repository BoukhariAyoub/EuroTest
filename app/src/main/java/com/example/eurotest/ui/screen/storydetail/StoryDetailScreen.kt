package com.example.eurotest.ui.screen.storydetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.eurotest.R
import com.example.eurotest.ui.StoryUi
import com.example.eurotest.ui.screen.articles.OverlappingImageTitle

@Composable
fun StoryDetailScreen(viewModel: StoryDetailViewModel = hiltViewModel()) {
    viewModel.uiState.value.story?.let {
        StoryItem(story = it)
    }
}

@Composable
fun StoryItem(
    modifier: Modifier = Modifier,
    story: StoryUi
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OverlappingImageTitle(sport = story.sport, imageUrl = story.image)
        Spacer(modifier = Modifier.padding(4.dp))
        TitleHeadline(title = story.title)
        Spacer(modifier = Modifier.padding(4.dp))
        AuthorAndDate(author = story.author, date = story.date)
        Spacer(modifier = Modifier.padding(4.dp))
        SubtitleHeadline(title = story.teaser)
        Spacer(modifier = Modifier.padding(2.dp))
        ArticleBody(
            text = story.teaser
        )
    }
    TransparentAppBar()
}

@Composable
fun TitleHeadline(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        text = title,
        modifier = modifier.fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.h6
    )
}

@Composable
fun SubtitleHeadline(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        text = title,
        modifier = modifier.fillMaxWidth(),
        style = MaterialTheme.typography.h6,
        fontSize = 14.sp
    )
}

@Composable
fun TransparentAppBar(navController: NavController = rememberNavController()) {
    TopAppBar(
        actions = {
            IconButton(onClick = { /*Nothing for now it is juste a poc*/ }) {
                Icon(Icons.Filled.Share, "backIcon", tint = Color.White)
            }
        },
        title = {
            Box(modifier = Modifier) {
                Text(text = "")
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    //FIXME : for some reason this is not working
                    //tried to debug by navController.backQueue but it seems always empty
                    navController.navigateUp()
                }) {
                Icon(Icons.Filled.KeyboardArrowLeft, "backIcon", tint = Color.White)
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun AuthorAndDate(
    author: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(start = 4.dp)) {
        Row {
            Text(text = stringResource(R.string.By))
            Text(text = author, color = Color.Blue, modifier = Modifier.padding(start = 2.dp))
        }
        Text(text = date, color = Color.Gray, fontSize = 14.sp)
    }
}


@Composable
fun ArticleBody(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        modifier = modifier.fillMaxWidth(),
        style = MaterialTheme.typography.body2,
        fontSize = 12.sp
    )
}

@Preview
@Composable
fun PrevStory() {
    MaterialTheme {
        StoryItem(
            story = StoryUi(
                id = 1,
                title = "Headline",
                teaser = "Another title that goes as a teaser for the article",
                image = "https://www.google.com/url?sa=i&url=https%3A%2F%2Folympics.com%2Fen%2Fnews%2Fuefa-champions-league-ucl-winners-list-football-club-teams&psig=AOvVaw3YKs9S-jm3iE_y-ZCwtmT9&ust=1665162788331000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCOiIz_WMzPoCFQAAAAAdAAAAABAD",
                date = "two days ago",
                author = "Ayoub",
                sport = "Football"
            )
        )
    }
}