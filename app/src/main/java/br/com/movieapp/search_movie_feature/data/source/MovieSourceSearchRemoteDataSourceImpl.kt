package br.com.movieapp.search_movie_feature.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.data.remote.response.SearchResponse
import br.com.movieapp.core.paging.MovieSearchPagingSource
import br.com.movieapp.search_movie_feature.domain.source.MovieSourceSearchRemoteDataSource
import javax.inject.Inject

class MovieSourceSearchRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : MovieSourceSearchRemoteDataSource {

    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(query = query, remoteDataSource = this)
    }

    override suspend fun getSearchMovies(query: String, page: Int): SearchResponse {
        return movieService.searchMovie(page = page, query = query)
    }
}