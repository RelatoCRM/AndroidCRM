package com.jsborbon.relato

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jsborbon.relato.ui.theme.RelatoTheme

class MainActivity() : ComponentActivity() {
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            RelatoTheme {
                navHostController = rememberNavController()
                NavigationWrapper(navHostController)

            }
        }
    }
}