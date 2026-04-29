package gvsu.cis.edu.cis357finalexam

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*

@Composable
fun NavGraph(
    vm: NetworkViewModel
) {
    val nav = rememberNavController()
    val state by vm.state.collectAsState()

    NavHost(
        navController = nav,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(vm, nav)
        }

        composable("detail/{id}") { backStack ->
            val id = backStack.arguments?.getString("id") ?: ""
            val network = state.allNetworks.find { it.id == id }

            if (network != null) {
                DetailScreen(
                    network,
                    vm,
                    nav
                )
            } else {
                // Show loading or handle missing network (e.g. after process death)
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}