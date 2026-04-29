package gvsu.cis.edu.cis357finalexam

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreen(
    item: Network,
    vm: NetworkViewModel,
    nav: NavController
) {
    val stars by vm.starred.collectAsState(
        initial = emptyList()
    )

    val starred = stars.any { it.id == item.id }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = item.name ?: "Unknown Network",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "${item.location?.city ?: "Unknown City"}, ${item.location?.country ?: "Unknown Country"}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(30.dp))

        Row {
            Button(
                onClick = { vm.toggleStar(item) }
            ) {
                Text(if (starred) "Unstar" else "Star")
            }

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = { nav.popBackStack() }
            ) {
                Text("Back")
            }
        }
    }
}