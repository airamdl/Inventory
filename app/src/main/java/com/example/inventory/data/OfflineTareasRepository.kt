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

package com.example.inventory.data

import com.example.inventory.data.Dao.TareaDao
import com.example.inventory.data.entity.Tarea
import kotlinx.coroutines.flow.Flow

class OfflineTareasRepository(private val tareaDao: TareaDao) : TareasRepository {
    override fun getAllTareasStream(): Flow<List<Tarea>> = tareaDao.getAllTareas()

    override fun getTareaStream(id: Int): Flow<Tarea?> = tareaDao.getTarea(id)

    override suspend fun insertTarea(tarea: Tarea) = tareaDao.insert(tarea)

    override suspend fun deleteTarea(tarea: Tarea) = tareaDao.delete(tarea)

    override suspend fun updateTarea(tarea: Tarea) = tareaDao.update(tarea)
}
