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

package com.example.inventory.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventory.data.Dao.TareaDao
import com.example.inventory.data.Dao.TipoTareaDao
import com.example.inventory.data.entity.Tarea
import com.example.inventory.data.entity.TipoTarea

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [Tarea::class, TipoTarea::class], version = 1, exportSchema = true)
abstract class TareaDatabase : RoomDatabase() {

    abstract fun tareaDao(): TareaDao
    abstract fun tipoTareaDao(): TipoTareaDao

    companion object {
        @Volatile
        private var Instance: TareaDatabase? = null

        fun getDatabase(context: Context): TareaDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TareaDatabase::class.java, "tarea_database")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
