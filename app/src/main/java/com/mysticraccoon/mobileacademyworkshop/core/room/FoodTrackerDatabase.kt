package com.mysticraccoon.mobileacademyworkshop.core.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mysticraccoon.mobileacademyworkshop.core.utils.FoodTrackerConstants
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodCategory
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem

@Database(entities = [FoodItem::class, FoodCategory::class], version = 1, exportSchema = false)
abstract class FoodTrackerDatabase: RoomDatabase() {

    abstract fun foodCategoryDao(): FoodCategoryDao
    abstract fun foodItemDao(): FoodItemDao

    companion object{

        @Volatile //always up to date and same to all execution threads
        private var INSTANCE: FoodTrackerDatabase? = null

        fun getInstance(context: Context): FoodTrackerDatabase {
            synchronized(this) { //means only one thread can enter this code at once
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        FoodTrackerDatabase::class.java,
                        FoodTrackerConstants.databaseName)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}