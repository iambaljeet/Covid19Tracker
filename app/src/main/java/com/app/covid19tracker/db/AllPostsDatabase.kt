package com.app.covid19tracker.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.covid19tracker.model.CountryCaseModel
import com.app.covid19tracker.model.DailyDataModel
import com.app.covid19tracker.model.GlobalDataModel

@Database(entities = [GlobalDataModel::class, CountryCaseModel::class, DailyDataModel.DailyDataModelItem::class], version = 1)
@TypeConverters(DataTypeConverter::class)
abstract class AllPostsDatabase : RoomDatabase() {
    abstract fun getAllPostsDao(): DatabaseDao

    companion object {
        const val DatabaseName = "covid19_db"

        fun getDatabase(application: Application): AllPostsDatabase {
            return Room.databaseBuilder(application, AllPostsDatabase::class.java, DatabaseName)
                .build()
        }
    }
}