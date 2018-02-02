package me.isaacdjl.disco.di

import dagger.BindsInstance
import dagger.Component
import me.isaacdjl.disco.application.DiscoApplication
import javax.inject.Singleton
import dagger.android.support.AndroidSupportInjectionModule


/**
 * Component that builds the object graph and declares in what type of object the dependencies provided
 * should be injected.
 *
 * @author Isaac Jensen-Large
 */

@Singleton
@Component(modules = arrayOf(AppModule::class,
        IntroDataModelModule::class,
        BuildersModule::class,
        AndroidSupportInjectionModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: DiscoApplication): Builder
        @BindsInstance
        fun introDataModelModule(introDataModelModule: IntroDataModelModule): Builder
        @BindsInstance
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }
    fun inject(application: DiscoApplication)
}