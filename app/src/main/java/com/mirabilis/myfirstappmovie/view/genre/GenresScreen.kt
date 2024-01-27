package com.mirabilis.myfirstappmovie.view.genre

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mirabilis.myfirstappmovie.R
import com.mirabilis.myfirstappmovie.domain.entity.Genre
import com.mirabilis.myfirstappmovie.view.theme.MyFirstAppMovieTheme

@Composable
fun GenresScreen() {
    val viewModel = GenresViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadGenres()
    }

    GenresContent { state }
}

@Composable
fun GenresContent(state: () -> GenresScreenState) {
    if (state().isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color.Red
                ),
                text = stringResource(id = R.string.genres)
            )

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        Text(
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                color = Color.Red
            ),
            text = stringResource(id = R.string.genres)
        )

        GenreListView { state().genres }
    }
}

@Composable
fun GenreItemView(genre: Genre) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            genre.name
        )
    }
}

@Composable
fun GenreListView(onGenres: () -> List<Genre>) {
    LazyColumn{
        items(onGenres()) { GenreItemView(it) }
    }
}

@Composable
@Preview
fun PreviewGenresScreen() {
    MyFirstAppMovieTheme {
        GenresContent { GenresScreenState() }
    }
}

@Composable
@Preview
fun PreviewGenreItem() {
    MyFirstAppMovieTheme {
        GenreItemView(genre = Genre(1, "Horror"))
    }
}
