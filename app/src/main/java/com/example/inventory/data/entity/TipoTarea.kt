package com.example.inventory.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey



    @Entity(tableName = "tipotareas")
    data class TipoTarea(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val titulo: String,
        val descripcion : String
    )


