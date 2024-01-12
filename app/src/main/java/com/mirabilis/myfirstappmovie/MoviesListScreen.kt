package com.mirabilis.myfirstappmovie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mirabilis.myfirstappmovie.entity.Movie
import com.mirabilis.myfirstappmovie.ui.theme.MyFirstAppMovieTheme

@Composable
fun MoviesListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                color = Color.DarkGray
            ),
            text = "Movies"
        )
        MoviesList()
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Row(
       modifier = Modifier
           .fillMaxWidth()
           .wrapContentHeight()
           .padding(8.dp)
    ) {
        Text(movie.name)
    }
}

@Composable
fun MoviesList() {
    val movies = listOf(
        Movie(1, "Poderoso Chefão"),
        Movie(2, "Senhor dos Anéis"),
        Movie(3, "Pulp fiction")
    )

    LazyColumn {
        items(movies) { MovieItem(movie = it) }
    }
}

@Preview
@Composable
fun PreviewMovieList() {
    MyFirstAppMovieTheme {
        MoviesList()
    }
}

@Preview
@Composable
fun PreviewMovieItem() {
    MyFirstAppMovieTheme {
        MovieItem(movie = Movie(1, "Nome do Filme"))
    }
}

@Preview
@Composable
fun PreviewMoviesListScreen() {
    MyFirstAppMovieTheme {
        MoviesListScreen()
    }
}
