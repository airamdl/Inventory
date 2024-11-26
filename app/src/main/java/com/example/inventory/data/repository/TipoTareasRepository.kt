package com.example.inventory.data.repository

import com.example.inventory.data.entity.Tarea
import com.example.inventory.data.entity.TipoTarea
import kotlinx.coroutines.flow.Flow

interface TipoTareasRepository {


        fun getAllTipoTareasStream(): Flow<List<TipoTarea>>

        /**
         * Retrieve an TipoTarea from the given data source that matches with the [id].
         */
        fun getTipoTareaStream(id: Int): Flow<TipoTarea?>

        /**
         * Insert TipoTarea in the data source
         */
        suspend fun insertTipoTarea(tipoTarea: TipoTarea)

        /**
         * Delete TipoTarea from the data source
         */
        suspend fun deleteTipoTarea(tipoTarea: TipoTarea)

        /**
         * Update TipoTarea in the data source
         */
        suspend fun updateTipoTarea(tipoTarea: TipoTarea)
}

