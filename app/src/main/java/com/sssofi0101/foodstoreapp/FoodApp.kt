package com.sssofi0101.foodstoreapp

import android.app.Application
import androidx.room.Room
import com.sssofi0101.foodstoreapp.data.room.AppDatabase
import com.sssofi0101.foodstoreapp.data.room.DatabseImpl

class FoodApp:Application() {

    companion object{
        lateinit var database: DatabseImpl
    }

    override fun onCreate() {
        super.onCreate()
        val roomInstance = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        database = DatabseImpl(roomInstance)

    }
}