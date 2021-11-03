package vboyko.gb.libs.lesson1

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GithubUsersRepo {

    private val api = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
        .create(GitHubApi::class.java)

    fun getUsers() = api.getUsers()
}