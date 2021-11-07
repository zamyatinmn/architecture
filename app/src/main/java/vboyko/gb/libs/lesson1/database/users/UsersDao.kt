package vboyko.gb.libs.lesson1.database.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<Users>

    @Insert
    fun insertAll(vararg user: Users)

    @Query("DELETE FROM users")
    fun deleteAll()
}