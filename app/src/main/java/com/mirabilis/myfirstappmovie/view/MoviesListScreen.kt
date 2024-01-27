package com.mirabilis.myfirstappmovie.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mirabilis.myfirstappmovie.R
import com.mirabilis.myfirstappmovie.domain.entity.Movie
import com.mirabilis.myfirstappmovie.view.theme.MyFirstAppMovieTheme

/**
 * Componente VISUAL que lida com a tela
 */
@Composable
fun MoviesListScreen() {
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
            .padding(5.dp),
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
                color = Color.Magenta,
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

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically


    ) {


        Text(
            "Diretor: ${movie.diretor}",
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.DarkGray
            )

        )
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically


    ) {


        Text(
            "Sinopse: ${movie.sinopse}",
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.DarkGray
            )

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
        Movie(1," - O Exorcista", "William Friedkin", "Em Georgetown, Washington, uma atriz vai gradativamente tomando consciência que a sua filha de doze anos está tendo um comportamento completamente assustador. Deste modo, ela pede ajuda a um padre, que também um psiquiatra, e este chega a conclusão de que a garota está possuída pelo demônio. Ele solicita então a ajuda de um segundo sacerdote, especialista em exorcismo, para tentar livrar a menina desta terrível possessão.", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4ucLGcXVVSVnsfkGtbLY4XAius8.jpg"),
        Movie(2," - O Iluminado", "Stanley Kubrick", "Durante o inverno, um homem é contratado para ficar como vigia em um hotel no Colorado e vai para lá com a esposa e seu filho. Porém, o contínuo isolamento começa a lhe causar problemas mentais sérios e ele vai se tornado cada vez mais agressivo e perigoso, ao mesmo tempo que seu filho passa a ter visões de acontecimentos ocorridos no passado, que também foram causados pelo isolamento excessivo.", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nRj5511mZdTl4saWEPoj9QroTIu.jpg"),
        Movie(3," - Alien Oitavo Passageiro", "Ridley Scott", "Quando a tripulação da sonda espacial Nostromo responde a um pedido de socorro vindo de um planeta inóspito, eles descobrem uma forma de vida mortal que se reproduz dentro de humanos. Agora, a tripulação deve lutar para permanecer viva e impedir que a criatura chegue até a Terra.", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vfrQk5IPloGg1v9Rzbh2Eg3VGyM.jpg"),
        Movie(4," - Halloween 1978", "John Carpenter","Na noite de Halloween de 1963, Michael Myers, de seis anos, assassina brutalmente sua irmã. Preso em uma instituição para doentes mentais sob os cuidados do dr. Sam Loomis, Michael cresce apenas para odiar. Em outubro de 1978, ele foge do hospital, seguido pelo dr. Loomis, que sabe que Michael vai matar novamente. O psicopata passa a perseguir a adolescente Laurie Strode e seus amigos, e prepara seu ataque mortal para a noite de Halloween.", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wijlZ3HaYMvlDTPqJoTCWKFkCPU.jpg"),
        Movie(5," - Hereditario", "Ari Aster", "Após a morte da reclusa avó, a família Graham começa a desvendar algumas coisas. Mesmo após a partida da matriarca, ela permanece como se fosse uma sombra sobre a família, especialmente sobre a solitária neta adolescente, Charlie, por quem ela sempre manteve uma fascinação não usual. Com um crescente terror tomando conta da casa, a família explora lugares mais escuros para escapar do infeliz destino que herdaram.", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3EEVihxNbFIOvnUkBJIJK93Jcn.jpg"),
        Movie(6," - Enigma de Outro Mundo", "John Carpenter","Na remota Antártida, um grupo de cientistas americanos é perturbado em sua base quando, de um helicóptero, alguém atira em um cão do acampamento. À medida que socorrem o cão baleado, o bicho começa a atacar os cientistas e os outros cachorros e logo eles descobrem que o animal pode assumir a forma de suas vítimas. Isto significa que membros da equipe podem ser mortos e a cópia assumir o lugar deles. Com isso, um piloto e um médico precisam capturar a fera antes que seja tarde demais.", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/a9RjXOIIB56k2rGoL3Fk3nRHHHQ.jpg")
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
       //MovieItemView(movie = Movie(1,"Nome do Filme"))
    }
}

@Preview
@Composable
fun PreviewMoviesListScreen2(){
    MyFirstAppMovieTheme {
        MoviesListScreen()
    }
}