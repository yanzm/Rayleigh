package ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import model.ApiResult
import remote.BlueSkyApi
import remote.ErrorResponse
import remote.TimelineResponse
import viewmodel.CommonViewModel

class HomeViewModel(
    private val api: BlueSkyApi
) : CommonViewModel() {

    var uiState by mutableStateOf<UiState>(UiState.Initial)
        private set

    init {
        load()
    }

    private fun load() {
        if (uiState is UiState.Loading) {
            return
        }

        uiState = UiState.Loading

        viewModelScope.launch {
            uiState = when (val result = api.getTimeline()) {
                is ApiResult.Success -> UiState.Success(result.data)
                is ApiResult.Error -> UiState.Error(result.e)
            }
        }
    }

    sealed interface UiState {
        object Initial : UiState
        object Loading : UiState
        data class Success(val timeline: TimelineResponse) : UiState
        data class Error(val e: ErrorResponse) : UiState
    }
}
