package me.isaacdjl.disco.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.isaacdjl.disco.ui.intro.MainIntroActivity

/**
 * Binds all subcomponents in the app
 *
 * @author Isaac Jensen-Large
 */

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = arrayOf(IntroModule::class))
    abstract fun bindMainIntroActivity(): MainIntroActivity
}
