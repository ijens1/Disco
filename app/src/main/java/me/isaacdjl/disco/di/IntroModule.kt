package me.isaacdjl.disco.di

import dagger.Module
import dagger.Provides
import me.isaacdjl.disco.ViewModelFactory
import me.isaacdjl.disco.application.DiscoApplication
import me.isaacdjl.disco.data.repository.Repository
import me.isaacdjl.disco.data.repository.RepositoryImpl
import javax.inject.Singleton

/**
 * Defines intro specific dependencies here
 *
 * @author: Isaac Jensen-Large
 */

@Module
class IntroModule {

    @Provides
    @Singleton
    fun provideRepository(app: DiscoApplication): Repository = RepositoryImpl(app)

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: Repository): ViewModelFactory = ViewModelFactory(repository)
}