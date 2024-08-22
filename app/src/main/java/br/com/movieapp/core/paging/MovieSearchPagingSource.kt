package br.com.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.movieapp.core.data.domain.MovieSearch
import br.com.movieapp.search_movie_feature.data.mapper.toMovieSearch
import br.com.movieapp.search_movie_feature.domain.source.MovieSourceSearchRemoteDataSource
import coil.network.HttpException
import java.io.IOException

class MovieSearchPagingSource(
    private val query: String,
    private val remoteDataSource: MovieSourceSearchRemoteDataSource,
) : PagingSource<Int, MovieSearch>() {
    override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getSearchMovies(query = query, page = pageNumber)
            val movies = response.searchResults

            LoadResult.Page(
                data = movies.toMovieSearch(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (movies.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}