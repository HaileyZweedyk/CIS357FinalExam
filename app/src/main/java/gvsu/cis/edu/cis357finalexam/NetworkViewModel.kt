package gvsu.cis.edu.cis357finalexam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class UiState(
    val visibleNetworks: List<Network> = emptyList(),
    val allNetworks: List<Network> = emptyList()
)

class NetworkViewModel(
    private val repo: CityBikeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    val starred = repo.starredItems()

    init {
        loadNetworks()
    }

    fun loadNetworks() {

        val testData = listOf(
            Network(
                "divvy",
                "Divvy",
                Location("Chicago","US")
            ),

            Network(
                "bicing",
                "Bicing",
                Location("Barcelona","ES")
            ),

            Network(
                "velib",
                "Velib",
                Location("Paris","FR")
            )
        )

        _state.value = UiState(
            allNetworks = testData,
            visibleNetworks = testData
        )
    }

    fun search(q: String) {
        if (q.isBlank()) {
            _state.value = _state.value.copy(
                visibleNetworks = _state.value.allNetworks
            )
            return
        }

        _state.value = _state.value.copy(
            visibleNetworks = _state.value.allNetworks.filter {
                (it.name?.contains(q, true) == true) ||
                        (it.location?.city?.contains(q, true) == true)
            }
        )
    }

    fun sortCountry() {
        _state.value = _state.value.copy(
            visibleNetworks = _state.value.visibleNetworks.sortedBy {
                it.location?.country ?: ""
            }
        )
    }

    fun sortName() {
        _state.value = _state.value.copy(
            visibleNetworks = _state.value.visibleNetworks.sortedBy {
                it.name ?: ""
            }
        )
    }

    fun toggleStar(network: Network) {
        viewModelScope.launch {
            repo.toggleStar(network)
        }
    }
}