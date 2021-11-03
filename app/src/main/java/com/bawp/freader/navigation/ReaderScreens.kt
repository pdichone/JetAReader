package com.bawp.freader.navigation

import java.lang.IllegalArgumentException

enum class ReaderScreens {
     SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    ReaderHomeScreen,
    SearchScreen,
    DetailScreen,
    UpdateScreen,
    ReaderStatsScreen;

    companion object {
         fun fromRoute(route: String?): ReaderScreens
          = when(route?.substringBefore("/")) {
              SplashScreen.name -> SplashScreen
             LoginScreen.name -> LoginScreen
             CreateAccountScreen.name -> CreateAccountScreen
             ReaderHomeScreen.name -> ReaderHomeScreen
             SearchScreen.name -> SearchScreen
             DetailScreen.name -> DetailScreen
             UpdateScreen.name -> UpdateScreen
             ReaderStatsScreen.name -> ReaderStatsScreen
             null -> ReaderHomeScreen
             else -> throw IllegalArgumentException("Route $route is not recognized")
          }
    }


}