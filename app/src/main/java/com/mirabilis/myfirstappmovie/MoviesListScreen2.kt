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

fun MoviesListScreen2() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ){
        Text(
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                color = Color.Red
            ),
            text = "Horror Movies"
        )
        MoviesList2()
    }
}

@Composable
fun MovieItem2(movie: Movie){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)

    ){
        Text(
            movie.id.toString(),
            style = TextStyle(
                color = Color.Blue
            )
        )
        Text(movie.name)
    }
}
       

@Composable
fun MoviesList2 (){
    val movies = listOf(
        Movie(1," - O Exorcista"),
        Movie(2," - O Iluminado"),
        Movie(3," - Alien Oitavo Passageiro"),
        Movie(4," - Halloween 1978"),
        Movie(5," - Hereditario"),
        Movie(6," - Enigma de Outro Mundo")
    )
    LazyColumn{
        items(movies) { MovieItem2(movie = it) }
    }
}

@Preview
@Composable

fun PreviewMovieList2(){
    MyFirstAppMovieTheme{
        MoviesList2()
    }
}

@Preview
@Composable
fun PreviewMovieItem2(){
    MyFirstAppMovieTheme {
       MovieItem2(movie = Movie(1,"Nome do Filme"))
    }
}

@Preview
@Composable
fun PreviewMoviesListScreen2(){
    MyFirstAppMovieTheme {
        MoviesListScreen2()
    }
}