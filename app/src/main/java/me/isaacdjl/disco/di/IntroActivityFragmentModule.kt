package me.isaacdjl.disco.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.isaacdjl.disco.ui.intro.DateTimePrefSlideFragment
import me.isaacdjl.disco.ui.intro.FoodPrefSlideFragment
import me.isaacdjl.disco.ui.intro.LocationPrefSlideFragment

/**
 * Module to assure generated subcomponents of the intro fragments are subcomponents of intro activity
 * components
 *
 * @author Isaac Jensen-Large
 */

@Module
abstract class IntroActivityFragmentModule {
    @ContributesAndroidInjector
    abstract fun bindFoodPrefSlideFragment(): FoodPrefSlideFragment

    @ContributesAndroidInjector
    abstract fun bindLocationPrefSlideFragment(): LocationPrefSlideFragment

    @ContributesAndroidInjector
    abstract fun bindDateTimePrefSlideFragment(): DateTimePrefSlideFragment
}