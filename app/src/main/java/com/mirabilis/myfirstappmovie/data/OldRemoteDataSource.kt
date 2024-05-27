package com.mirabilis.myfirstappmovie.data

import com.mirabilis.myfirstappmovie.data.entity.SignInResponse
import com.mirabilis.myfirstappmovie.data.entity.SignOutResponse
import com.mirabilis.myfirstappmovie.data.entity.SignUpResponse
import com.mirabilis.myfirstappmovie.data.network.API
import com.mirabilis.myfirstappmovie.data.network.NetworkProvider
import com.mirabilis.myfirstappmovie.domain.entity.GetGenres
import com.mirabilis.myfirstappmovie.domain.entity.Movie
import com.mirabilis.myfirstappmovie.domain.entity.NewMovie
import com.mirabilis.myfirstappmovie.domain.entity.movies.genre.GetMovieByGenre
import kotlinx.coroutines.delay
import retrofit2.Call
import java.lang.Thread.sleep
import kotlin.random.Random

/**
 * Fonte de dados
 */
class OldRemoteDataSource {

    private val api: API = NetworkProvider.api()

    fun getMovie(movieId: Long): Call<NewMovie> {
        return api.getOldMovie(movieId)
    }

    fun getGenres(): Call<GetGenres> {
        return api.getOldGenres()
    }

    fun getMoviesByGenre(id: Long): Call<GetMovieByGenre> {
        return api.getOldMoviesByGenre(id)
    }

    /**
     * Método que não busca informações do servidor de verdade (Simula)
     */
    fun searchMoviesBy(searchText: String? = null): List<Movie> {
        sleep(2000)

        val data = listOf(
            Movie(1,"O Exorcista", "William Friedkin", "Em Georgetown, Washington, uma atriz vai gradativamente tomando consciência que a sua filha de doze anos está tendo um comportamento completamente assustador. Deste modo, ela pede ajuda a um padre, que também um psiquiatra, e este chega a conclusão de que a garota está possuída pelo demônio. Ele solicita então a ajuda de um segundo sacerdote, especialista em exorcismo, para tentar livrar a menina desta terrível possessão.", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4ucLGcXVVSVnsfkGtbLY4XAius8.jpg"),
            Movie(2,"O Iluminado", "Stanley Kubrick", "Durante o inverno, um homem é contratado para ficar como vigia em um hotel no Colorado e vai para lá com a esposa e seu filho. Porém, o contínuo isolamento começa a lhe causar problemas mentais sérios e ele vai se tornado cada vez mais agressivo e perigoso, ao mesmo tempo que seu filho passa a ter visões de acontecimentos ocorridos no passado, que também foram causados pelo isolamento excessivo.", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nRj5511mZdTl4saWEPoj9QroTIu.jpg"),
            Movie(3,"Alien Oitavo Passageiro", "Ridley Scott", "Quando a tripulação da sonda espacial Nostromo responde a um pedido de socorro vindo de um planeta inóspito, eles descobrem uma forma de vida mortal que se reproduz dentro de humanos. Agora, a tripulação deve lutar para permanecer viva e impedir que a criatura chegue até a Terra.", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vfrQk5IPloGg1v9Rzbh2Eg3VGyM.jpg"),
            Movie(4,"Halloween 1978", "John Carpenter","Na noite de Halloween de 1963, Michael Myers, de seis anos, assassina brutalmente sua irmã. Preso em uma instituição para doentes mentais sob os cuidados do dr. Sam Loomis, Michael cresce apenas para odiar. Em outubro de 1978, ele foge do hospital, seguido pelo dr. Loomis, que sabe que Michael vai matar novamente. O psicopata passa a perseguir a adolescente Laurie Strode e seus amigos, e prepara seu ataque mortal para a noite de Halloween.", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wijlZ3HaYMvlDTPqJoTCWKFkCPU.jpg"),
            Movie(5,"Hereditario", "Ari Aster", "Após a morte da reclusa avó, a família Graham começa a desvendar algumas coisas. Mesmo após a partida da matriarca, ela permanece como se fosse uma sombra sobre a família, especialmente sobre a solitária neta adolescente, Charlie, por quem ela sempre manteve uma fascinação não usual. Com um crescente terror tomando conta da casa, a família explora lugares mais escuros para escapar do infeliz destino que herdaram.", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3EEVihxNbFIOvnUkBJIJK93Jcn.jpg"),
            Movie(6,"Enigma de Outro Mundo", "John Carpenter","Na remota Antártida, um grupo de cientistas americanos é perturbado em sua base quando, de um helicóptero, alguém atira em um cão do acampamento. À medida que socorrem o cão baleado, o bicho começa a atacar os cientistas e os outros cachorros e logo eles descobrem que o animal pode assumir a forma de suas vítimas. Isto significa que membros da equipe podem ser mortos e a cópia assumir o lugar deles. Com isso, um piloto e um médico precisam capturar a fera antes que seja tarde demais.", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/a9RjXOIIB56k2rGoL3Fk3nRHHHQ.jpg")
        )

        if (searchText == null) {
            return data
        }

        val filteredMovies = arrayListOf<Movie>()

       data.forEach { movie ->
            if (movie.name.contains(searchText, true)) {
                filteredMovies.add(movie)
            }
        }
        
        return filteredMovies
    }

    /**
     * Método que FINGE fazer o login (simula)
     */
    suspend fun signIn(email: String, password: String): SignInResponse {
        delay(2000)
        if (Random.nextBoolean()) {
            return SignInResponse(
                email = email,
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9lbWFpbGFkZHJlc3MiOiJyb2RyaWdvQGdhbWVnb2xmLmNvbSIsImV4cCI6MTc0NDg0MDQ3NywiaXNzIjoiaHR0cHM6Ly9sb2NhbGhvc3Q6MzI3NzQiLCJhdWQiOiJodHRwczovL2xvY2FsaG9zdDozMjc3NCJ9.Mu5c-rPoMd1iRz-UIGej2p0hl-0_0xuzr3vkVpgfQ9M"
            )
        }

        throw Exception("Simulação de erro no servidor!")
    }

    suspend fun signOut(token: String?): SignOutResponse {
        delay(2000)
        if (Random.nextBoolean()) {
            return SignOutResponse(
                success = true
            )
        }

        throw Exception("Simulação de erro no servidor!")
    }

    suspend fun signUp(email: String, password: String): SignUpResponse {
        delay(2000)
        if (Random.nextBoolean()) {
            return SignUpResponse(
                email = email,
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9lbWFpbGFkZHJlc3MiOiJyb2RyaWdvQGdhbWVnb2xmLmNvbSIsImV4cCI6MTc0NDg0MDQ3NywiaXNzIjoiaHR0cHM6Ly9sb2NhbGhvc3Q6MzI3NzQiLCJhdWQiOiJodHRwczovL2xvY2FsaG9zdDozMjc3NCJ9.Mu5c-rPoMd1iRz-UIGej2p0hl-0_0xuzr3vkVpgfQ9M"
            )
        }

        throw Exception("Simulação de erro no servidor!")
    }
}