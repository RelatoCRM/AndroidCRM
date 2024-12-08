package com.jsborbon.relato

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jsborbon.relato.components.LatestReviews
import com.jsborbon.relato.ui.theme.RelatoTheme

class HomeView() : ComponentActivity() {
    private var selectedIndex = 0
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            RelatoTheme {
                navHostController = rememberNavController()
                NavigationWrapper(navHostController)


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(onClick = {}) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    },
                    bottomBar = {
                        NavigationBar {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                BottomBarIcon(
                                    selected = selectedIndex == 0,
                                    onClick = {
                                        selectedIndex = 0
                                        navHostController.navigate("home")
                                              },
                                    icon = R.drawable.ic_home,
                                    contentDescription = "Home"
                                )
                                BottomBarIcon(
                                    selected = selectedIndex == 1,
                                    onClick = { selectedIndex = 1 },
                                    icon = R.drawable.ic_calendar,
                                    contentDescription = "Calendar"
                                )
                                BottomBarIcon(
                                    selected = selectedIndex == 2,
                                    onClick = { selectedIndex = 2 },
                                    icon = R.drawable.ic_mail_and_text_magnifyingglass,
                                    contentDescription = "Management"
                                )
                                BottomBarIcon(
                                    selected = selectedIndex == 3,
                                    onClick = { selectedIndex = 3 },
                                    icon = R.drawable.ic_mailbox,
                                    contentDescription = "Mailbox"
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                "Welcome back!",
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.headlineMedium
                            )
                            Text(
                                "Manolo Ruíz",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                "4 new notifications",
                                style = MaterialTheme.typography.headlineMedium,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.companyicon),
                                contentDescription = "Relato Logo",
                                modifier = Modifier
                                    .size(70.dp)
                                    .aspectRatio(1f)
                            )
                        }

                        ChartView()

                        LatestReviews()
                    }
                }
            }
        }
    }

    @Composable
    fun BottomBarIcon(
        selected: Boolean,
        onClick: () -> Unit,
        icon: Int,
        contentDescription: String
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = contentDescription,
            modifier = Modifier
                .size(24.dp)
                .clickable { onClick() },
            tint = if (selected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    RelatoTheme {
        HomeView()
    }
}