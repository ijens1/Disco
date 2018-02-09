package me.isaacdjl.disco.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import me.isaacdjl.disco.application.DiscoApplication
import javax.inject.Singleton

/**
 * Module that provides context injection
 *
 * @author Isaac Jensen-Large
 */

@Module
class DiscoAppModule(private val app: Application) {
}