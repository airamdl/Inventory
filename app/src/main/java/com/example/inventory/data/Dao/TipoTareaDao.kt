package com.example.inventory.data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inventory.data.entity.Tarea
import com.example.inventory.data.entity.TipoTarea
import kotlinx.coroutines.flow.Flow

@Dao
interface TipoTareaDao {

    @Query("SELECT * from tipotareas ORDER BY titulo ASC")
    fun getAllTareas(): Flow<List<TipoTarea>>

    @Query("SELECT * from tipotareas WHERE id = :id")
    fun getTarea(id: Int): Flow<TipoTarea>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tipoTarea: TipoTarea)

    @Update
    suspend fun update(tipoTarea: TipoTarea)

    @Delete
    suspend fun delete(tipoTarea: TipoTarea)
}