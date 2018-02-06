package me.isaacdjl.disco.di

import dagger.Module
import dagger.android.ActivityKey
import dagger.android.ContributesAndroidInjector
import me.isaacdjl.disco.ui.intro.IntroActivityScope
import me.isaacdjl.disco.ui.intro.MainIntroActivity

/**
 * Binds all subcomponents in the app
 *
 * @author Isaac Jensen-Large
 */

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = arrayOf(IntroModule::class, IntroActivityFragmentModule::class))
    @IntroActivityScope
    abstract fun bindMainIntroActivity(): MainIntroActivity
}
