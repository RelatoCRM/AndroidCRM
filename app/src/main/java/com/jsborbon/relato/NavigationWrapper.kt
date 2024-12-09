package com.jsborbon.relato

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

object Routes {
    const val Home = "HomeView"
    const val Calendar = "CalendarView"
    const val Communications = "CommunicationsView"
    const val Configuration = "ConfigurationView"
    const val Management = "ManagementView"
    const val Insights = "InsightsView"
}

@Composable
fun NavigationWrapper (navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Routes.Home) {

        composable(Routes.Home) { HomeView(navHostController) }
        composable(Routes.Calendar) { CalendarView(navHostController) }
        composable(Routes.Communications) {CommunicationsView(navHostController)}
        composable(Routes.Configuration) {ConfigurationView(navHostController)}
        composable(Routes.Management) { ManagementView(navHostController)}
        composable(Routes.Insights) { InsightsView(navHostController)}


    }
}

