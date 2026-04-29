package gvsu.cis.edu.cis357finalexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        val repo = CityBikeRepository(
            ApiService(),
            FakeStarDao()
        )

        val vm = NetworkViewModel(repo)

        setContent {
            NavGraph(vm)
        }
    }
}