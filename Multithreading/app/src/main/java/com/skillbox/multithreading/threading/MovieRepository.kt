package com.skillbox.multithreading.threading

import android.util.Log
import com.skillbox.multithreading.networking.Movie
import com.skillbox.multithreading.networking.Network
import java.util.*

class MovieRepository {

    private fun getMovieById(movieId: String): Movie? {
        return Network.api().getMovieById(movieId, Network.MOVIE_API_KEY).execute()
            .body()
    }

    fun fetchMovies(
        movieIds: List<String>
    ): MutableList<Movie> {
        val allMovies = Collections.synchronizedList(mutableListOf<Movie>())

        Thread {

            val threads = movieIds.chunked(2).map { movieChunk ->
                Thread {
                    val movies = movieChunk.mapNotNull { movieId ->
                        getMovieById(movieId)
                    }
                    allMovies.addAll(movies)
                }
            }

            threads.forEach { it.start() }
            threads.forEach { it.join() }

        }.start()
        return allMovies
    }
}