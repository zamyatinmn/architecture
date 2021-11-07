package vboyko.gb.libs.lesson1.database.repos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ReposDao {
    @Query("SELECT * FROM repos WHERE owner LIKE :ownerId")
    fun getAll(ownerId: Long)

    @Insert
    fun insertAll(vararg repo: Repos)

    @Query("DELETE FROM repos WHERE owner LIKE :ownerId")
    fun deleteAll(ownerId: Long)
}