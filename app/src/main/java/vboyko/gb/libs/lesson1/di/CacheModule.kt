package vboyko.gb.libs.lesson1.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import vboyko.gb.libs.lesson1.App
import vboyko.gb.libs.lesson1.database.AppDatabase
import vboyko.gb.libs.lesson1.database.Cache
import vboyko.gb.libs.lesson1.database.RoomGithubRepositoriesCache
import vboyko.gb.libs.lesson1.database.RoomGithubUsersCache
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): AppDatabase = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "main.db"
    ).build()

    @Singleton
    @Provides
    fun usersCache(database: AppDatabase): RoomGithubUsersCache = RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: AppDatabase): RoomGithubRepositoriesCache =
        RoomGithubRepositoriesCache(database)

    @Singleton
    @Provides
    fun cache(
        usersCache: RoomGithubUsersCache,
        repositoriesCache: RoomGithubRepositoriesCache
    ): Cache = Cache(usersCache, repositoriesCache)
}