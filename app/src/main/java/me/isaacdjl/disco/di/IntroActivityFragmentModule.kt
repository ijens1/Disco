package me.isaacdjl.disco.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.isaacdjl.disco.ui.intro.FoodPreferencesFragment

/**
 * Module to assure generated subcomponents of the intro fragments are subcomponents of intro activity
 * components
 *
 * @author Isaac Jensen-Large
 */

@Module
abstract class IntroActivityFragmentModule {
    @ContributesAndroidInjector
    abstract fun bindFoodPreferenceFragment(): FoodPreferencesFragment
}