package br.com.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularScreen
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController= navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {


        composable(BottomNavItem.MoviePopular.route) {
        val viewModel: MoviePopularViewModel = hiltViewModel()
            MoviePopularScreen(
                uiState = viewModel.uiState,
                navigateToDetail = { movieId ->
                    //navController.navigate("movieDetailScreen/$movieId")
                }
            )
        }
        composable(BottomNavItem.MovieSearch.route) {
//            MovieSearchScreen()
        }
        composable(BottomNavItem.MovieFavorite.route) {
//            MovieFavoriteScreen()
        }
    }

}