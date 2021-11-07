package vboyko.gb.libs.lesson1.database

import vboyko.gb.libs.lesson1.Repo
import vboyko.gb.libs.lesson1.database.repos.Repos


class RoomGithubRepositoriesCache(private val db: AppDatabase) {
    fun getReposList(ownerId: Long) = db.reposDao().getAll(ownerId).mapToGhRepo()

    fun storeNewList(repos: List<Repo>, ownerId: Long) {
        db.reposDao().deleteAll(ownerId)
        db.reposDao().insertAll(*repos.mapToDbRepo(ownerId).toTypedArray())
    }

    private fun List<Repo>.mapToDbRepo(ownerId: Long): List<Repos> {
        return this.map {
            Repos(it.id, ownerId, it.name, it.forks_count)
        }
    }

    private fun List<Repos>.mapToGhRepo(): List<Repo> {
        return this.map {
            Repo(it.id, it.name, it.forks_count)
        }
    }
}