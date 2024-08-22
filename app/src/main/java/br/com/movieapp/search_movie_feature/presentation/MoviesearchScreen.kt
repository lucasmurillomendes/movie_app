package br.com.movieapp.search_movie_feature.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.search_movie_feature.presentation.components.SearchContent
import br.com.movieapp.search_movie_feature.presentation.state.MovieSearchState
import br.com.movieapp.ui.theme.black

@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (MoviesSearchEvent) -> Unit,
    onFetch: (String) -> Unit,
    navigateToDetail: (movieId: Int) -> Unit
) {
    val pageMovies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.search_movies))
                },
                backgroundColor = black
            )
        },
        content = { paddingValues ->
            SearchContent(
                paddingValues = paddingValues,
                pagingMovies = pageMovies,
                query = uiState.query,
                onSearch = onFetch,
                onEvent = onEvent,
                onDetail = navigateToDetail
            )
        }
    )
}