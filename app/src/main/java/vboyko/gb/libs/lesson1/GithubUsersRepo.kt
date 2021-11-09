package vboyko.gb.libs.lesson1

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import vboyko.gb.libs.lesson1.database.Cache

class GithubUsersRepo(private val cache: Cache, private val api: GitHubApi) {

    private val userBs = BehaviorSubject.create<Unit>()

    fun subscribeOnGithubUsersData(): Observable<List<GithubUser>> {
        return userBs
            .observeOn(Schedulers.io())
            .switchMap {
                Observable.combineLatest(
                    Observable.just(cache.usersCache.getUserList()),
                    api.getUsers().onErrorReturn { emptyList() }.toObservable(),
                    { fromDatabase, fromNetwork ->
                        if (fromNetwork.isEmpty()) {
                            fromDatabase
                        } else {
                            cache.usersCache.storeNewList(fromNetwork)
                            fromNetwork
                        }
                    }
                )
            }
    }

    fun getUsers() = userBs.onNext(Unit)

    private val repoBs = BehaviorSubject.create<GithubUser>()

    fun subscribeOnGithubReposData(): Observable<List<Repo>> {
        return repoBs
            .observeOn(Schedulers.io())
            .switchMap {
                Observable.combineLatest(
                    Observable.just(cache.reposCache.getReposList(it.id)),
                    api.getRepos(it.repos_url).onErrorReturn { emptyList() }.toObservable(),
                    { fromDatabase, fromNetwork ->
                        if (fromNetwork.isEmpty()) {
                            fromDatabase
                        } else {
                            cache.reposCache.storeNewList(fromNetwork, it.id)
                            fromNetwork
                        }
                    }
                )
            }
    }

    fun getRepos(user: GithubUser) = repoBs.onNext(user)
}