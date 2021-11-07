package vboyko.gb.libs.lesson1.database

import vboyko.gb.libs.lesson1.GithubUser
import vboyko.gb.libs.lesson1.database.users.Users


class RoomGithubUsersCache(private val db: AppDatabase) {

    fun getUserList() = db.usersDao().getAll().mapToGhUser()

    fun storeNewList(users: List<GithubUser>) {
        db.usersDao().deleteAll()
        db.usersDao().insertAll(*users.mapToDbUser().toTypedArray())
    }

    private fun List<GithubUser>.mapToDbUser(): List<Users> {
        return this.map {
            Users(
                it.login,
                it.id,
                it.node_id,
                it.avatar_url,
                it.gravatar_id,
                it.url,
                it.html_url,
                it.followers_url,
                it.following_url,
                it.gists_url,
                it.starred_url,
                it.subscriptions_url,
                it.organizations_url,
                it.repos_url,
                it.events_url,
                it.received_events_url,
                it.type,
                it.site_admin
            )
        }
    }

    private fun List<Users>.mapToGhUser(): List<GithubUser> {
        return this.map {
            GithubUser(
                it.login,
                it.id,
                it.node_id,
                it.avatar_url,
                it.gravatar_id,
                it.url,
                it.html_url,
                it.followers_url,
                it.following_url,
                it.gists_url,
                it.starred_url,
                it.subscriptions_url,
                it.organizations_url,
                it.repos_url,
                it.events_url,
                it.received_events_url,
                it.type,
                it.site_admin
            )
        }
    }
}