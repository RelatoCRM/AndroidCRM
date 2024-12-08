package com.jsborbon.relato

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


data class CommunicationClass(
    val subject: String,
    val communicationBody: String,
    val sendingGroup: String,
    val campaign: String,
    val future: Boolean
)

// Main communications screen
@Composable
fun CommunicationsView(navHostController: NavHostController) {
    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var showNewCommunicationDialog by remember { mutableStateOf(false) }

    val availableCategories = listOf(
        "All", "Announcements", "Ticketing", "Reminders", "Products"
    )

    var communications by remember { mutableStateOf(listOf(
        CommunicationClass("Q4 Keynote Reminder", "JSDJASJ", "Investors Newsletter", "Reminders", false),
        CommunicationClass("Product Tips", "JASJASJJS", "Tips Newsletter", "Products", false),
        CommunicationClass("Weekly Update", "JSJAJSJAS", "Weekly Digest", "Announcements", false),
        CommunicationClass("Project Deadline", "JSJSJJS", "Reminders & Deadlines", "Ticketing", false)
    )) }

    val filteredCommunications = communications.filter { communication ->
        (selectedCategory == null || selectedCategory == "All" || communication.campaign == selectedCategory) &&
                (searchText.isEmpty() || communication.subject.contains(searchText, true) ||
                        communication.sendingGroup.contains(searchText, true))
    }

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(Modifier.padding(16.dp)) {
            Text("Welcome to", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("Communications", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("Manolo Ruíz", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search bar
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search communications...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Categories scroll
        Row(
            Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            availableCategories.forEach { category ->
                Text(
                    text = category,
                    color = if (category == selectedCategory) Color.Blue else Color.Black,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { selectedCategory = category }
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Communications list
        Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(horizontal = 16.dp)) {
            filteredCommunications.forEach { communication ->
                CommunicationRow(communication)
                HorizontalDivider()

            }
        }

        Box(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(onClick = { showNewCommunicationDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "New Communication")
            }
        }
    }
}

@Composable
fun CommunicationRow(communication: CommunicationClass) {
    Row(Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(communication.subject, fontWeight = FontWeight.Bold)
            Text(communication.sendingGroup, color = Color.Gray)
        }
        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
    }
}


@Preview
@Composable
fun CommunicationsViewPreview() {
    CommunicationsView(navHostController = rememberNavController())
}