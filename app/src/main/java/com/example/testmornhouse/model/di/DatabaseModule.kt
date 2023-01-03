package com.example.testmornhouse.model.di

import android.content.Context
import androidx.room.Room
import com.example.testmornhouse.model.room.NumberFactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NumberFactDatabase {
        return Room.databaseBuilder(
            context,
            NumberFactDatabase::class.java, "database-facts"
        ).build()
    }
}