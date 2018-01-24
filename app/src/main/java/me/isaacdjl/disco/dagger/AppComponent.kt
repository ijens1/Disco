package me.isaacdjl.disco.dagger

import dagger.Component
import me.isaacdjl.disco.ui.intro.IntroDataModel
import javax.inject.Singleton

/**
 * Component that builds the object graph and declares in what type of object the dependencies provided
 * should be injected.
 *
 * @author Isaac Jensen-Large
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
}