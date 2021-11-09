package vboyko.gb.libs.lesson1.di

import dagger.Module
import dagger.Provides
import vboyko.gb.libs.lesson1.App

@Module
class AppModule(private val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}