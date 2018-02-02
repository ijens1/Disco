package me.isaacdjl.disco.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.isaacdjl.disco.ui.intro.MainIntroActivity

/**
 * Binds all subcomponents in the app
 */

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindMainIntroActivity(): MainIntroActivity
}
