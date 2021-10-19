package vboyko.gb.libs.lesson1

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private val repositories =
        (0..100).map { GithubUser("login $it") }

    fun getUsers(): Observable<GithubUser> {
        return Observable.fromIterable(repositories)
    }
}