package com.example.inventory.data.repository

import com.example.inventory.data.Dao.TipoTareaDao
import com.example.inventory.data.entity.TipoTarea
import kotlinx.coroutines.flow.Flow

class OfflineTipoTareasRepository(private val tipoTareaDao: TipoTareaDao) : TipoTareasRepository {
    override fun getAllTipoTareasStream(): Flow<List<TipoTarea>> = tipoTareaDao.getAllTareas()

    override fun getTipoTareaStream(id: Int): Flow<TipoTarea?> = tipoTareaDao.getTarea(id)

    override suspend fun insertTipoTarea(tipoTarea: TipoTarea) = tipoTareaDao.insert(tipoTarea)

    override suspend fun deleteTipoTarea(tipoTarea: TipoTarea) = tipoTareaDao.delete(tipoTarea)

    override suspend fun updateTipoTarea(tipoTarea: TipoTarea) = tipoTareaDao.update(tipoTarea)
}