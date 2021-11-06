package com.bawp.freader.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.bawp.freader.screens.ReaderSplashScreen
import com.bawp.freader.screens.details.BookDetailsScreen
import com.bawp.freader.screens.home.Home
import com.bawp.freader.screens.login.ReaderLoginScreen
import com.bawp.freader.screens.search.BooksSearchViewModel
import com.bawp.freader.screens.search.SearchScreen
import com.bawp.freader.screens.stats.ReaderStatsScreen


@ExperimentalComposeUiApi
@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = ReaderScreens.SplashScreen.name ){

         composable(ReaderScreens.SplashScreen.name) {
             ReaderSplashScreen(navController = navController)
         }
        composable(ReaderScreens.LoginScreen.name) {
            ReaderLoginScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderStatsScreen.name) {
            ReaderStatsScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderHomeScreen.name) {
            Home(navController = navController)
        }

        composable(ReaderScreens.SearchScreen.name) {
            val searchViewModel = hiltViewModel<BooksSearchViewModel>()

            SearchScreen(navController = navController, viewModel = searchViewModel)
        }

        val detailName = ReaderScreens.DetailScreen.name
        composable("$detailName/{bookId}", arguments = listOf(navArgument("bookId"){
            type = NavType.StringType
        })) { backStackEntry ->
              backStackEntry.arguments?.getString("bookId").let {

                  BookDetailsScreen(navController = navController, bookId = it.toString())
              }
        }

    }

}