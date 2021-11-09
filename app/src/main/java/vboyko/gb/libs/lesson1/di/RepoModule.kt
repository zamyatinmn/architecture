package vboyko.gb.libs.lesson1.di

import dagger.Module
import dagger.Provides
import vboyko.gb.libs.lesson1.GitHubApi
import vboyko.gb.libs.lesson1.GithubUsersRepo
import vboyko.gb.libs.lesson1.database.Cache
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(cache: Cache, client: GitHubApi): GithubUsersRepo = GithubUsersRepo(cache, client)
}