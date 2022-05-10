package com.example.cs330_dz06.di

import android.content.Context
import com.example.cs330_dz06.data.db.DatabaseHandler
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
    fun provideApplication(@ApplicationContext app: Context):BaseApplication{
        return app as BaseApplication
    }


}