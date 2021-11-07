package vboyko.gb.libs.lesson1

import android.app.Application
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import vboyko.gb.libs.lesson1.database.AppDatabase
import vboyko.gb.libs.lesson1.database.Cache
import vboyko.gb.libs.lesson1.database.RoomGithubRepositoriesCache
import vboyko.gb.libs.lesson1.database.RoomGithubUsersCache

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "main.db"
        ).build()
    }
    private val usersCache: RoomGithubUsersCache by lazy { RoomGithubUsersCache(db) }
    private val reposCache: RoomGithubRepositoriesCache by lazy { RoomGithubRepositoriesCache(db) }

    val cache: Cache by lazy { Cache(usersCache, reposCache) }

    //Временно до даггера положим это тут
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}