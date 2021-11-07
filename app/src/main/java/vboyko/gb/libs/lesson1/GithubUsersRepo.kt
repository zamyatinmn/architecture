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

    private val bs = BehaviorSubject.create<Unit>()

    fun subscribeOnGithubUsersData(): Observable<List<GithubUser>> {
        return bs
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

    fun getUsers() = bs.onNext(Unit)

    fun getRepos(url: String) = api.getRepos(url)
}