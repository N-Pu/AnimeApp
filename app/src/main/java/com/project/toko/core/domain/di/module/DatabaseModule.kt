package com.project.toko.core.domain.di.module

import android.content.Context
import androidx.room.Room
import com.project.toko.core.data.dao.MainDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMainDb(@ApplicationContext context: Context): MainDb = Room.databaseBuilder(
        context.applicationContext,
        MainDb::class.java,
        "Main.db"
    ).fallbackToDestructiveMigration().build()
}
