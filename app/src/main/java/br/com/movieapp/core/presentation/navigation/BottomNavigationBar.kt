package br.com.movieapp.core.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.yellow

@Composable
fun BottomNavigationBar(
    navController: NavController,
) {
    val itens = listOf(
        BottomNavItem.MoviePopular,
        BottomNavItem.MovieSearch,
        BottomNavItem.MovieFavorite,
    )

    BottomNavigation(
        contentColor = yellow,
        backgroundColor = black
    ) {
        // Retorna o ultimo item da pilha de navegação (mais recente)
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        itens.forEach { destination ->
            BottomNavigationItem(
                selected = currentRoute == destination.route,
                onClick = {
                    navController.navigate(destination.route) {
                        launchSingleTop = true
                    }
                },
                icon = { Icon(destination.icon, contentDescription = destination.title) },
                label = { Text(text = destination.title)},
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}