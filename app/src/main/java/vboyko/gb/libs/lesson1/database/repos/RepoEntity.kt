package vboyko.gb.libs.lesson1.database.repos

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Repos(
    @PrimaryKey
    val id: Long,
    val owner: Long = 0,
    val name: String,
    val forks_count: Int
)