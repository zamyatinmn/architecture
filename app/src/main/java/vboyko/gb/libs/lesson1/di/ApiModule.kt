package vboyko.gb.libs.lesson1.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vboyko.gb.libs.lesson1.GitHubApi
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Named("baseUrl")
    fun baseUrl(): String = "https://api.github.com/"

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    fun api(
        @Named("baseUrl") baseUrl: String,
        gson: Gson
    ): GitHubApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(GitHubApi::class.java)
}