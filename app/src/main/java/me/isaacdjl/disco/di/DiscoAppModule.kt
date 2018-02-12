package me.isaacdjl.disco.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import me.isaacdjl.disco.application.DiscoApplication
import me.isaacdjl.disco.data.database.AppDatabase
import me.isaacdjl.disco.data.database.daos.UserPrefsDao
import javax.inject.Singleton

/**
 * Module that provides context injection
 *
 * @author Isaac Jensen-Large
 */

@Module
class DiscoAppModule(private val app: Application) {

    @Provides
    fun provideContext(): Context = app

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "user-prefs-db").build()

    @Provides
    fun providesUserPrefsDao(database: AppDatabase): UserPrefsDao = database.userPrefsDao()
}