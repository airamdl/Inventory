package com.example.inventory.ui.tipotarea

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.repository.TareasRepository
import com.example.inventory.data.repository.TipoTareasRepository
import com.example.inventory.ui.tarea.ItemDetailsDestination
import com.example.inventory.ui.tarea.TareaDetails
import com.example.inventory.ui.tarea.toItem
import com.example.inventory.ui.tarea.toItemDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val tipoTareasRepository: TipoTareasRepository,
) : ViewModel() {

    private val tareaId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.tareaIdArg])

    /**
     * Holds the item details ui state. The data is retrieved from [ItemsRepository] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<TipoTareaDetailsUiState> =
        tipoTareasRepository.getTipoTareaStream(tareaId)
            .filterNotNull()
            .map {
                TipoTareaDetailsUiState(outOfStock = it.idTipoTarea <= 0, tipoTareaDetails = it.toItemDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TipoTareaDetailsUiState()
            )


//    fun reduceQuantityByOne() {
//        viewModelScope.launch {
//            val currentTarea = uiState.value.tareaDetails.toItem()
//            if (currentTarea.quantity > 0) {
//                tareasRepository.updateTarea(currentTarea.copy(quantity = currentTarea.quantity - 1))
//            }
//        }
//    }

    /**
     * Deletes the item from the [ItemsRepository]'s data source.
     */
    suspend fun deleteTipoTarea() {
        tipoTareasRepository.deleteTipoTarea(uiState.value.tipoTareaDetails.toItem())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for ItemDetailsScreen
 */
data class TipoTareaDetailsUiState(
    val tipoTareaDetails: TareaDetails = TareaDetails()
)
