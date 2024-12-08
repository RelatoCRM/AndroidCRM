package com.jsborbon.relato

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationWrapper (navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = "HomeView") {

        composable("CalendarView") { CalendarView(navHostController) }
        composable("CommunicationsView") {CommunicationsView(navHostController)}
        composable("ConfigurationView") {ConfigurationView(navHostController)}
        composable("ManagementView") { ManagementView(navHostController)}
        composable("InsightsView") { InsightsView(navHostController)}


    }
}

