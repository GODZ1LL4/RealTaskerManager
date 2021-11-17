package com.example.realtasker.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tarea_table")
@Parcelize
data class Tarea(
    @PrimaryKey(autoGenerate = true)
    val Id: Int,
    @ColumnInfo(name = "descripcion")
    val Descripcion: String,
    @ColumnInfo(name = "completada")
    var Completada: Boolean
) : Parcelable
