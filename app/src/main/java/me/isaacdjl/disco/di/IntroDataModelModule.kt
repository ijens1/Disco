package me.isaacdjl.disco.di

import android.app.Application
import dagger.Module
import dagger.Provides
import me.isaacdjl.disco.ui.intro.IntroDataModelImpl
import me.isaacdjl.disco.ui.intro.IntroDataModelInterface
import javax.inject.Singleton

/**
 * Provides the implementation of the IntroDataModel
 *
 * @author: Isaac Jensen-Large
 */
@Module
class IntroDataModelModule(private val app: Application) {
}