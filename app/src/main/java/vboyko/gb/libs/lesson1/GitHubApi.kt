package vboyko.gb.libs.lesson1

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url


/**
 * Created by Maxim Zamyatin on 31.10.2021
 */


interface GitHubApi {
    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepos(@Url url: String): Single<List<Repo>>
}