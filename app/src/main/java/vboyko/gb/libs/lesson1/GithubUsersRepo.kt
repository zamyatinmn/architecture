package vboyko.gb.libs.lesson1

import com.google.gson.Gson
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vboyko.gb.libs.lesson1.database.Cache

class GithubUsersRepo(private val cache: Cache) {

    private val api = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
        .create(GitHubApi::class.java)

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