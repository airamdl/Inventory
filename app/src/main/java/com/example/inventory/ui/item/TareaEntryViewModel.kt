/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.entity.Tarea
import com.example.inventory.data.repository.TareasRepository

/**
 * ViewModel to validate and insert items in the Room database.
 */
class ItemEntryViewModel(private val tareasRepository: TareasRepository) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var tareaUiState by mutableStateOf(TareaUiState())
        private set

    /**
     * Updates the [tareaUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(tareaDetails: TareaDetails) {
        tareaUiState =
            TareaUiState(tareaDetails = tareaDetails, isEntryValid = validateInput(tareaDetails))
    }

    /**
     * Inserts an [Item] in the Room database
     */
    suspend fun saveTarea() {
        if (validateInput()) {
            tareasRepository.insertTarea(tareaUiState.tareaDetails.toItem())
        }
    }

    private fun validateInput(uiState: TareaDetails = tareaUiState.tareaDetails): Boolean {
        return with(uiState) {
            titulo.isNotBlank() && descripcion.isNotBlank() && idTipoTarea.toString().isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Item.
 */
data class TareaUiState(
    val tareaDetails: TareaDetails = TareaDetails(),
    val isEntryValid: Boolean = false
)

data class TareaDetails(
    val id: Int = 0,
    val titulo: String = "",
    val descripcion: String = "",
    val idTipoTarea: Int = 0,
)

/**
 * Extension function to convert [TareaUiState] to [Item]. If the value of [TareaDetails.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [TareaUiState] is not a valid [Int], then the quantity will be set to 0
 */
fun TareaDetails.toItem(): Tarea = Tarea(
    id = id,
    titulo = titulo,
    descripcion = descripcion,
    idTipoTarea = idTipoTarea
)

//fun Tarea.formatedPrice(): String {
//    return NumberFormat.getCurrencyInstance().format(price)
//}

/**
 * Extension function to convert [Item] to [TareaUiState]
 */
fun Tarea.toItemUiState(isEntryValid: Boolean = false): TareaUiState = TareaUiState(
    tareaDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [TareaDetails]
 */
fun Tarea.toItemDetails(): TareaDetails = TareaDetails(
    id = id,
    titulo = titulo,
    descripcion = descripcion,
    idTipoTarea = idTipoTarea
)
