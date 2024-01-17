package com.mirabilis.myfirstappmovie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import com.mirabilis.myfirstappmovie.entity.Movie
import com.mirabilis.myfirstappmovie.ui.theme.MyFirstAppMovieTheme

/**
 * Componente VISUAL que lida com a tela
 */
@Composable
fun MoviesListScreen2() {
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
            text = "Horror Movies"
        )
        MoviesListView()
    }
}

//https://www.themoviedb.org/
//Criar descrição e o nome do diretor em cada filme

/**
 * Componente VISUAL que lida com cada item da lista
 */
@Composable
fun MovieItemView(movie: Movie){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var style = TextStyle(
            color = Color.Red,
            fontWeight = FontWeight(1000)
        )

        /**
         * Se for par
         */
        if (movie.id % 2 == 0) {
            style = TextStyle(
                color = Color.Green,
                fontWeight = FontWeight(1000)
            )
        }

        MovieImageView(url = movie.url)
        Text(
            movie.id.toString(),
            style = style
        )

        Text(
            movie.name,
            style = style
        )

    }
}

/**
 * Componente VISUAL que lida com a imagem e o seu fundo
 */
@Composable
fun MovieImageView(url: String?) {
    Surface(
        modifier = Modifier
            .size(width = 50.dp, height = 80.dp)
            .padding(end = 8.dp)
    ) {
        AsyncImage(
            model = url,
            contentDescription = stringResource(id = R.string.text_do_icone_do_filme),
        )

        /*Image(
            modifier = Modifier
                .padding(8.dp),
            painter = painterResource(id = R.drawable.filme),
            contentDescription = stringResource(id = R.string.text_do_icone_do_filme)
        )*/
    }
}


/**
 * Componente VISUAL que lida com a lista
 */
@Composable
fun MoviesListView() {
    val movies = listOf(
        Movie(1," - O Exorcista", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4ucLGcXVVSVnsfkGtbLY4XAius8.jpg"),
        Movie(2," - O Iluminado", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nRj5511mZdTl4saWEPoj9QroTIu.jpg"),
        Movie(3," - Alien Oitavo Passageiro", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vfrQk5IPloGg1v9Rzbh2Eg3VGyM.jpg"),
        Movie(4," - Halloween 1978", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wijlZ3HaYMvlDTPqJoTCWKFkCPU.jpg"),
        Movie(5," - Hereditario", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3EEVihxNbFIOvnUkBJIJK93Jcn.jpg"),
        Movie(6," - Enigma de Outro Mundo", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/a9RjXOIIB56k2rGoL3Fk3nRHHHQ.jpg")
    )

    LazyColumn{
        items(movies) { MovieItemView(movie = it) }
    }
}






/**
 * Componentes VISUAIS que lidam com a pre visualização dentro do android studio
 */

@Preview
@Composable
fun PreviewMovieImage() {
    MyFirstAppMovieTheme {
        MovieImageView("\"https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4ucLGcXVVSVnsfkGtbLY4XAius8.jpg\"")
    }
}
@Preview
@Composable
fun PreviewMovieList2(){
    MyFirstAppMovieTheme{
        MoviesListView()
    }
}

@Preview
@Composable
fun PreviewMovieItem2(){
    MyFirstAppMovieTheme {
       MovieItemView(movie = Movie(1,"Nome do Filme"))
    }
}

@Preview
@Composable
fun PreviewMoviesListScreen2(){
    MyFirstAppMovieTheme {
        MoviesListScreen2()
    }
}