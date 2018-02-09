package me.isaacdjl.disco.di

import dagger.Module
import dagger.Provides
import me.isaacdjl.disco.ViewModelFactory
import me.isaacdjl.disco.application.DiscoApplication
import me.isaacdjl.disco.data.repository.Repository
import me.isaacdjl.disco.data.repository.RepositoryImpl

/**
 * Defines intro specific dependencies here
 *
 * @author Isaac Jensen-Large
 */

@Module
class IntroModule {

    @Provides
    @ActivityScope
    fun provideRepository(app: DiscoApplication): Repository = RepositoryImpl(app)

    @Provides
    @ActivityScope
    fun provideViewModelFactory(repository: Repository): ViewModelFactory = ViewModelFactory(repository)
}