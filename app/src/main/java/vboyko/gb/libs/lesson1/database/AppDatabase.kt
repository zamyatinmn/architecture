package vboyko.gb.libs.lesson1.database

import androidx.room.Database
import androidx.room.RoomDatabase
import vboyko.gb.libs.lesson1.database.repos.Repos
import vboyko.gb.libs.lesson1.database.repos.ReposDao
import vboyko.gb.libs.lesson1.database.users.Users
import vboyko.gb.libs.lesson1.database.users.UsersDao


/**
 * Created by Maxim Zamyatin on 07.11.2021
 */

@Database(entities = [Users::class, Repos::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun reposDao(): ReposDao
}