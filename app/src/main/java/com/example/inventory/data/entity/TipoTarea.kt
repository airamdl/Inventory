package com.example.inventory.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation



    @Entity(tableName = "tipotareas")
    data class TipoTarea(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val titulo: String,
        val descripcion : String
    )


