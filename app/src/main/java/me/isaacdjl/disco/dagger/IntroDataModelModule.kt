package me.isaacdjl.disco.dagger

import android.app.Application
import dagger.Provides
import me.isaacdjl.disco.ui.intro.IntroDataModelImpl
import me.isaacdjl.disco.ui.intro.IntroDataModelInterface
import javax.inject.Singleton

/**
 * Provides the implementation of the IntroDataModel
 *
 * @author: Isaac Jensen-Large
 */
class IntroDataModelModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideIntroDataModel(): IntroDataModelInterface = IntroDataModelImpl(app)
}