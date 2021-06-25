package com.leavingston.exchangerates.room


import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leavingston.exchangerates.models.Example
import com.leavingston.exchangerates.models.ratesModel

const val DATABASE_VERSION = 2
const val DATABASE_NAME = "rates_database"
@Database(
    entities = [ratesModel::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract fun dao() : DAO
    companion object{
        @Volatile
        private var instance : DataBase ?= null

        fun getDatabase(context: Context) : DataBase{
        if (instance == null){
            instance = Room.databaseBuilder(
                context,
                DataBase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration()
                .build()
        }
            return instance!!




        }
    }

}












