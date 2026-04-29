package gvsu.cis.edu.cis357finalexam

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(
    vm: NetworkViewModel,
    nav: NavController
) {
    val state by vm.state.collectAsState()
    val stars by vm.starred.collectAsState(
        initial = emptyList()
    )

    var search by remember {
        mutableStateOf("")
    }

    Column(
        Modifier.fillMaxSize()
    ) {
        Row(
            Modifier.padding(8.dp)
        ) {
            TextField(
                value = search,
                onValueChange = {
                    search = it
                    // Optional: live search
                    vm.search(it)
                },
                modifier = Modifier.weight(1f),
                label = { Text("Search city or name") },
                singleLine = true
            )

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = {
                    vm.search(search)
                }
            ) {
                Text("Search")
            }
        }

        if (state.visibleNetworks.isEmpty()) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("No networks found")
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = state.visibleNetworks,
                    key = { it.id ?: it.hashCode() }
                ) { network ->
                    NetworkRow(
                        network,
                        stars.any {
                            it.id == network.id
                        }
                    ) {
                        network.id?.let { id ->
                            nav.navigate("detail/$id")
                        }
                    }
                }
            }
        }

        Row(
            Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { vm.sortCountry() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Sort Country")
            }

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = { vm.sortName() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Sort Name")
            }
        }
    }
}