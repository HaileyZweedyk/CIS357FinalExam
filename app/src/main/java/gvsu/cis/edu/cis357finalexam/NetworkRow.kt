package gvsu.cis.edu.cis357finalexam

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp

@Composable
fun NetworkRow(
    network: Network,
    starred: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(2.dp, Color.Blue),
        colors = CardDefaults.cardColors(
            containerColor = if (starred) Color(0xFFDDEECC) else Color.White
        ),
        onClick = onClick
    ) {
        Column(
            Modifier.padding(12.dp)
        ) {
            Text(
                text = network.name ?: "Unknown Network",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${network.location?.city ?: "Unknown City"}, ${network.location?.country ?: "Unknown Country"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}