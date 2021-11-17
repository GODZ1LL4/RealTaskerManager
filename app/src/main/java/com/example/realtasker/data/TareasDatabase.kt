package com.example.realtasker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.realtasker.model.Tarea


@Database(entities = [Tarea::class], version = 1, exportSchema = false)
public abstract class TareasDatabase : RoomDatabase() {

    abstract fun tareaDao(): TareaDao

    companion object {

        var instance: TareasDatabase? = null

        @JvmStatic
        public fun getInstance(context: Context?): TareasDatabase {
            if (instance == null) {
                val db = Room.databaseBuilder(
                    context!!,
                    TareasDatabase::class.java, "tarea-db"
                ).build()
                instance = db
            }
            return instance!!
        }
    }
}
